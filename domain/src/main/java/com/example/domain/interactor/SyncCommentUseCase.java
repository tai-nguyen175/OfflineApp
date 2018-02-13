package com.example.domain.interactor;

import com.example.domain.repository.RemoteCommentRepository;
import com.example.domain.model.Comment;

import io.reactivex.Completable;

/**
 * Responsible for syncing a comment with remote repository.
 */
public class SyncCommentUseCase {
    private final RemoteCommentRepository remoteCommentRepository;

    public SyncCommentUseCase(RemoteCommentRepository remoteCommentRepository) {
        this.remoteCommentRepository = remoteCommentRepository;
    }

    public Completable syncComment(Comment comment) {
        return remoteCommentRepository.sync(comment);
    }
}
