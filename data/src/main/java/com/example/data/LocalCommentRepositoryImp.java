package com.example.data;

import com.example.domain.model.Comment;
import com.example.domain.model.CommentUtils;
import com.example.domain.repository.LocalCommentRepository;
import com.example.mapper.CommentMapper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import timber.log.Timber;

public class LocalCommentRepositoryImp implements LocalCommentRepository {

    private final AppDao appDao;
    private final CommentMapper commentMapper;

    public LocalCommentRepositoryImp(AppDao appDao, CommentMapper commentMapper) {
        this.appDao = appDao;
        this.commentMapper = commentMapper;
    }

    /**
     * Adds a comment to a given photo
     */
    public Single<Comment> add(long photoId, String commentText) {
        Timber.d("creating comment for photo id %s, comment text %s", photoId, commentText);
        Comment comment = new Comment(photoId, commentText);

        return Single.fromCallable(() -> {
            long rowId = appDao.add(commentMapper.reverse(comment));
            Timber.d("comment stored " + rowId);
            return CommentUtils.clone(comment, rowId);
        });
    }

    /**
     * Updates a comment
     */
    public Completable update(Comment comment) {
        Timber.d("updating comment sync status for comment id %s", comment.getId());

        return Completable.fromAction(() -> appDao.update(commentMapper.reverse(comment)));
    }

    /**
     * Deletes a comment
     */
    public Completable delete(Comment comment) {
        Timber.d("deleting comment with id %s", comment.getId());

        return Completable.fromAction(() -> appDao.delete(commentMapper.reverse(comment)));
    }

    /**
     * Returns Flowable stream of getComments for a given photo
     */
    public Flowable<List<Comment>> getComments(long photoId) {
        Timber.d("getting getComments for photo id %s", photoId);
        return appDao.getComments(photoId).map(commentMapper::map);
    }
}