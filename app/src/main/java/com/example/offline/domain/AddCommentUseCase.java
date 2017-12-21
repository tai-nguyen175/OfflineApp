package com.example.offline.domain;

import com.example.offline.domain.repository.LocalCommentRepository;

import io.reactivex.Completable;

import static com.example.offline.domain.model.Comment.DUMMY_PHOTO_ID;

public class AddCommentUseCase {
    private final LocalCommentRepository localCommentRepository;
    private final SyncCommentUseCase syncCommentUseCase;

    public AddCommentUseCase(LocalCommentRepository localCommentRepository, SyncCommentUseCase syncCommentUseCase) {
        this.localCommentRepository = localCommentRepository;
        this.syncCommentUseCase = syncCommentUseCase;
    }

    public Completable addComment(String commentText) {
        return localCommentRepository.add(DUMMY_PHOTO_ID, commentText)
                .flatMapCompletable(syncCommentUseCase::syncComment);
    }
}
