package com.example.services.jobqueue;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.example.data.networking.Resource;
import com.example.data.networking.RestAPIFactory;
import com.example.domain.model.Comment;
import com.example.domain.model.CommentUtils;
import com.example.model.exception.ResponseException;
import com.example.services.SyncCommentRxBus;

import timber.log.Timber;

public class SyncCommentJob extends Job {

    private static final String TAG = SyncCommentJob.class.getCanonicalName();
    private final Comment comment;

    public SyncCommentJob(Comment comment) {
        super(new Params(JobPriority.MID)
                .requireNetwork()
                .groupBy(TAG)
                .persist());
        this.comment = comment;
    }
    @Override
    public void onAdded() {
        Timber.d("Executing onAdded() for comment " + comment);
    }

    @Override
    public void onRun() throws Throwable {
        Timber.d("Executing onRun() for comment " + comment);

        // if any exception is thrown, it will be handled by shouldReRunOnThrowable()
        RestAPIFactory.getInstance().addComment(comment);

        // remote call was successful--the Comment will be updated locally to reflect that sync is no longer pending
        Comment updatedComment = CommentUtils.clone(comment, false);
        SyncCommentRxBus.getInstance().post(Resource.success(updatedComment));
    }

    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
        Timber.d("canceling job. reason: %d, throwable: %s", cancelReason, throwable);
        // sync to remote failed
        SyncCommentRxBus.getInstance().post(Resource.failed("", comment));
    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        if(throwable instanceof ResponseException) {
            ResponseException exception = (ResponseException) throwable;

            int statusCode = exception.getResponse().code();
            if (statusCode >= 400 && statusCode < 500) {
                return RetryConstraint.CANCEL;
            }
        }
        // if we are here, most likely the connection was lost during job execution
        return RetryConstraint.RETRY;
    }
}
