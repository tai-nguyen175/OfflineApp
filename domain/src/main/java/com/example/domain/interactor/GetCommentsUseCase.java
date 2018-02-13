package com.example.domain.interactor;

import com.example.domain.repository.LocalCommentRepository;
import com.example.domain.model.Comment;

import java.util.List;

import io.reactivex.Flowable;

import static com.example.domain.model.Comment.DUMMY_PHOTO_ID;

public class GetCommentsUseCase {
    private final LocalCommentRepository localCommentRepository;

    public GetCommentsUseCase(LocalCommentRepository localCommentRepository) {
        this.localCommentRepository = localCommentRepository;
    }

    public Flowable<List<Comment>> getComments() {
        return localCommentRepository.getComments(DUMMY_PHOTO_ID);
    }
}
