package com.example.data.networking;


import com.example.services.SyncResponseEventType;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * A generic class that holds a value with its loading liveDataState.
 *
 * @param <T>
 */
public class Resource<T> {
    @NonNull
    public final SyncResponseEventType eventType;
    @Nullable
    public final T data;
    @Nullable
    public final String message;

    public Resource(@NonNull SyncResponseEventType liveDataState, @Nullable T data, @Nullable String message) {
        this.eventType = liveDataState;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SyncResponseEventType.SUCCESS, data, null);
    }

    public static <T> Resource<T> failed(String msg, @Nullable T data) {
        return new Resource<>(SyncResponseEventType.FAILED, data, msg);
    }

    public static <T> Resource<T> sending(@Nullable T data) {
        return new Resource<>(SyncResponseEventType.SENDING, data, null);
    }
}