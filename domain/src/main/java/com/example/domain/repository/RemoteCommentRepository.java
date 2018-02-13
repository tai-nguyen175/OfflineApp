package com.example.domain.repository;

import com.example.domain.model.Comment;

import io.reactivex.Completable;

public interface RemoteCommentRepository {
    Completable sync(Comment comment);
}
