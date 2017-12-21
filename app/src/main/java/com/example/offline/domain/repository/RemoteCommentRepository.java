package com.example.offline.domain.repository;

import com.example.offline.domain.model.Comment;

import io.reactivex.Completable;

public interface RemoteCommentRepository {
    Completable sync(Comment comment);
}
