package com.example.data;

import com.example.domain.repository.RemoteCommentRepository;
import com.example.services.jobqueue.JobManagerFactory;
import com.example.services.jobqueue.SyncCommentJob;
import com.example.domain.model.Comment;

import io.reactivex.Completable;

public class RemoteCommentRepositoryImp implements RemoteCommentRepository {

    @Override
    public Completable sync(Comment comment) {
        return Completable.fromAction(() ->
                JobManagerFactory.getJobManager().addJobInBackground(new SyncCommentJob(comment)));
    }
}
