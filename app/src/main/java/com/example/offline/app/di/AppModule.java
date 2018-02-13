package com.example.offline.app.di;

import android.content.Context;

import com.example.data.AppDao;
import com.example.data.AppDatabase;
import com.example.data.LocalCommentRepositoryImp;
import com.example.data.RemoteCommentRepositoryImp;
import com.example.domain.interactor.DeleteCommentUseCase;
import com.example.domain.interactor.UpdateCommentUseCase;
import com.example.domain.repository.LocalCommentRepository;
import com.example.domain.repository.RemoteCommentRepository;
import com.example.mapper.CommentMapper;
import com.example.offline.App;
import com.example.services.jobqueue.GcmJobService;
import com.example.services.jobqueue.SchedulerJobService;
import com.example.services.jobqueue.SyncCommentJobManagerInitializer;
import com.example.services.jobqueue.SyncCommentResponseObserver;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This is where you will inject application-wide dependencies.
 */
@Module
public class AppModule {

    @Provides
    Context provideContext(App application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    SchedulerJobService provideSchedulerJobService() {
        return new SchedulerJobService();
    }

    @Singleton
    @Provides
    GcmJobService provideGcmJobService() {
        return new GcmJobService();
    }

    @Singleton
    @Provides
    AppDao provideCommentDao(Context context) {
        return AppDatabase.getInstance(context).commentDao();
    }

    @Singleton
    @Provides
    LocalCommentRepository provideLocalCommentRepository(AppDao appDao, CommentMapper commentMapper) {
        return new LocalCommentRepositoryImp(appDao, commentMapper);
    }

    @Singleton
    @Provides
    RemoteCommentRepository provideRemoteCommentRepository() {
        return new RemoteCommentRepositoryImp();
    }

    @Singleton
    @Provides
    SyncCommentResponseObserver provideSyncCommentResponseObserver(UpdateCommentUseCase updateCommentUseCase, DeleteCommentUseCase deleteCommentUseCase) {
        return new SyncCommentResponseObserver(updateCommentUseCase, deleteCommentUseCase);
    }

    @Singleton
    @Provides
    UpdateCommentUseCase provideUpdateCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new UpdateCommentUseCase(localCommentRepository);
    }

    @Singleton
    @Provides
    DeleteCommentUseCase provideDeleteCommentUseCase(LocalCommentRepository localCommentRepository) {
        return new DeleteCommentUseCase(localCommentRepository);
    }

    @Singleton
    @Provides
    SyncCommentJobManagerInitializer provideSyncCommentJobManagerInitializer(SyncCommentResponseObserver syncCommentResponseObserver) {
        return new SyncCommentJobManagerInitializer(syncCommentResponseObserver);
    }

    @Singleton
    @Provides
    CommentMapper provideCommentMapper() {
        return new CommentMapper();
    }
}
