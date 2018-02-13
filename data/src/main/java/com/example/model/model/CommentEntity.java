package com.example.model.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

@Entity
public class CommentEntity implements Serializable {
    public static final long DUMMY_PHOTO_ID = 1L;
    @Expose
    @PrimaryKey(autoGenerate = true)
    private long id;

    @Expose
    @ColumnInfo(name = "photo_id")
    private long photoId;

    @Expose
    @ColumnInfo(name = "comment_text")
    private String commentText;

    @Expose
    @ColumnInfo(name = "timestamp")
    private long timestamp;

    @ColumnInfo(name = "sync_pending")
    private boolean syncPending;

    @Ignore
    public CommentEntity() {

    }

    @Ignore
    public CommentEntity(long photoId, String commentText) {
        this.photoId = photoId;
        this.commentText = commentText;
        this.timestamp = System.currentTimeMillis();
        this.syncPending = true;
    }

    public CommentEntity(long id, long photoId, String commentText, long timestamp, boolean syncPending) {
        this.id = id;
        this.photoId = photoId;
        this.commentText = commentText;
        this.timestamp = timestamp;
        this.syncPending = syncPending;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setSyncPending(boolean syncPending) {
        this.syncPending = syncPending;
    }

    public long getId() {
        return id;
    }

    public long getPhotoId() {
        return photoId;
    }

    public String getCommentText() {
        return commentText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isSyncPending() {
        return syncPending;
    }

    @Override
    public String toString() {
        return String.format("CommentEntity id: %s, text: %s, syncPending: %s", id, commentText, syncPending);
    }
}
