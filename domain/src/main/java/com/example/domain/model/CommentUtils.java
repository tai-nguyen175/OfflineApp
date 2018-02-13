package com.example.domain.model;

public class CommentUtils {
    public static Comment clone(Comment from, boolean syncPending) {
        return new Comment(from.getId(), from.getPhoto_id(), from.getBody(),
                from.getTimestamp(), syncPending);
    }

    public static Comment clone(Comment from, long id) {
        return new Comment(id, from.getPhoto_id(), from.getBody(),
                from.getTimestamp(), from.isSyncPending());
    }
}
