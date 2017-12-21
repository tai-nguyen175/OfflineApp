package com.example.offline.domain;

import com.example.offline.domain.repository.LocalCommentRepository;
import com.example.offline.domain.model.Comment;

import io.reactivex.Completable;

public class UpdateCommentUseCase {
    private final LocalCommentRepository localCommentRepository;

    public UpdateCommentUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Completable updateComment(Comment comment) {
        return localCommentRepository.update(comment);
    }
}
