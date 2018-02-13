package com.example.model.model;

public class CommentUtils {
    public static CommentEntity clone(CommentEntity from, boolean syncPending) {
        return new CommentEntity(from.getId(), from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), syncPending);
    }

    public static CommentEntity clone(CommentEntity from, long id) {
        return new CommentEntity(id, from.getPhotoId(), from.getCommentText(),
                from.getTimestamp(), from.isSyncPending());
    }
}
