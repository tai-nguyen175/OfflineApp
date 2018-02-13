package com.example.domain.interactor;

import com.example.domain.model.Comment;
import com.example.domain.repository.LocalCommentRepository;

import io.reactivex.Completable;

public class DeleteCommentUseCase {
    private final LocalCommentRepository localCommentRepository;

    public DeleteCommentUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Completable deleteComment(Comment comment) {
        return localCommentRepository.delete(comment);
    }
}
