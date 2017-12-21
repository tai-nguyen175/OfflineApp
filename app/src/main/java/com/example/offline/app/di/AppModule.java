package com.example.offline.app.di;

import android.content.Context;

import com.example.offline.App;
import com.example.offline.data.AppDatabase;
import com.example.offline.data.AppDao;
import com.example.offline.data.LocalCommentRepositoryImp;
import com.example.offline.data.RemoteCommentRepositoryImp;
import com.example.offline.domain.DeleteCommentUseCase;
import com.example.offline.domain.repository.LocalCommentRepository;
import com.example.offline.domain.repository.RemoteCommentRepository;
import com.example.offline.domain.UpdateCommentUseCase;
import com.example.offline.domain.services.jobs.SyncCommentResponseObserver;
import com.example.offline.domain.services.jobs.GcmJobService;
import com.example.offline.domain.services.jobs.SchedulerJobService;
import com.example.offline.domain.services.jobs.SyncCommentJobManagerInitializer;

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
    LocalCommentRepository provideLocalCommentRepository(AppDao appDao) {
        return new LocalCommentRepositoryImp(appDao);
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
}
