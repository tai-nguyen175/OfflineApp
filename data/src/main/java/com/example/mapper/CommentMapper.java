package com.example.mapper;

import com.example.domain.Mapper;
import com.example.domain.model.Comment;
import com.example.model.model.CommentEntity;

/**
 * Created by Tai Long Nguyen on 2/13/18.
 */

public class CommentMapper extends Mapper<Comment, CommentEntity> {

    public CommentMapper() {

    }

    public CommentEntity reverse(Comment comment) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(comment.getId());
        commentEntity.setCommentText(comment.getBody());
        commentEntity.setPhotoId(comment.getPhoto_id());
        commentEntity.setTimestamp(comment.getTimestamp());
        commentEntity.setSyncPending(comment.isSyncPending());
        return commentEntity;
    }

    public Comment map(CommentEntity commentEntity) {
        Comment comment = new Comment();
        comment.setId(commentEntity.getId());
        comment.setBody(commentEntity.getCommentText());
        comment.setPhoto_id(commentEntity.getPhotoId());
        comment.setTimestamp(commentEntity.getTimestamp());
        comment.setSyncPending(commentEntity.isSyncPending());
        return comment;
    }

}
