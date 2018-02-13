package com.example.services.jobqueue;

import android.annotation.SuppressLint;
import android.support.annotation.WorkerThread;

import com.example.data.networking.Resource;
import com.example.domain.interactor.DeleteCommentUseCase;
import com.example.domain.interactor.UpdateCommentUseCase;
import com.example.domain.model.Comment;
import com.example.services.SyncCommentRxBus;
import com.example.services.SyncResponseEventType;

import timber.log.Timber;

public class SyncCommentResponseObserver {

    private final UpdateCommentUseCase updateCommentUseCase;
    private final DeleteCommentUseCase deleteCommentUseCase;

    public SyncCommentResponseObserver(UpdateCommentUseCase updateCommentUseCase,
                                       DeleteCommentUseCase deleteCommentUseCase) {
        this.updateCommentUseCase = updateCommentUseCase;
        this.deleteCommentUseCase = deleteCommentUseCase;
    }

    @SuppressLint("RxLeakedSubscription")
    void observeSyncResponse() {
        SyncCommentRxBus.getInstance().toObservable()
                .subscribe(this::handleSyncResponse,
                        t -> Timber.e(t, "error handling sync response"));
    }

    private void handleSyncResponse(Resource<Comment> response) {
        if (response.eventType == SyncResponseEventType.SUCCESS) {
            onSyncCommentSuccess(response.data);
        } else {
            onSyncCommentFailed(response.data);
        }
    }

    @SuppressLint("RxLeakedSubscription")
    @WorkerThread
    private void onSyncCommentSuccess(Comment comment) {
        Timber.d("received sync comment success event for comment %s", comment);
        updateCommentUseCase.updateComment(comment)
                .subscribe(() -> Timber.d("update comment success"),
                        t -> Timber.e(t, "update comment error"));
    }

    @SuppressLint("RxLeakedSubscription")
    @WorkerThread
    private void onSyncCommentFailed(Comment comment) {
        Timber.d("received sync comment failed event for comment %s", comment);
        deleteCommentUseCase.deleteComment(comment)
                .subscribe(() -> Timber.d("delete comment success"),
                        t -> Timber.e(t, "delete comment error"));
    }
}
