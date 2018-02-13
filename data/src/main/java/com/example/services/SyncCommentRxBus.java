package com.example.services;

import com.example.data.networking.Resource;
import com.example.domain.model.Comment;
import com.jakewharton.rxrelay2.PublishRelay;

import io.reactivex.Observable;

public class SyncCommentRxBus {

    private static SyncCommentRxBus instance;
    private final PublishRelay<Resource<Comment>> relay;

    public static synchronized SyncCommentRxBus getInstance() {
        if (instance == null) {
            instance = new SyncCommentRxBus();
        }
        return instance;
    }

    private SyncCommentRxBus() {
        relay = PublishRelay.create();
    }

    public void post(Resource<Comment> commentResponse) {
        relay.accept(commentResponse);
    }

    public Observable<Resource<Comment>> toObservable() {
        return relay;
    }
}
