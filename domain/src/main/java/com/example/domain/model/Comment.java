package com.example.domain.model;

import java.io.Serializable;

public class Comment implements Serializable {
    public static final long DUMMY_PHOTO_ID = 1L;
    private long id;

    private long photo_id;

    private String body;

    private long timestamp;

    private boolean syncPending;

    public Comment() {

    }
    public Comment(long photo_id, String body) {
        this.photo_id = photo_id;
        this.body = body;
        this.timestamp = System.currentTimeMillis();
        this.syncPending = true;
    }

    public Comment(long id, long photo_id, String body, long timestamp, boolean syncPending) {
        this.id = id;
        this.photo_id = photo_id;
        this.body = body;
        this.timestamp = timestamp;
        this.syncPending = syncPending;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(long photo_id) {
        this.photo_id = photo_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSyncPending() {
        return syncPending;
    }

    public void setSyncPending(boolean syncPending) {
        this.syncPending = syncPending;
    }

    @Override
    public String toString() {
        return String.format("Comment id: %s, text: %s, syncPending: %s", id, body, syncPending);
    }
}
