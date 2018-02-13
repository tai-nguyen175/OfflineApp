package com.example.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.model.model.CommentEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add(CommentEntity comment);

    @Update
    void update(CommentEntity comment);

    @Delete
    void delete(CommentEntity comment);

    @Query("SELECT * FROM CommentEntity WHERE photo_id = :photoId ORDER BY timestamp DESC")
    Flowable<List<CommentEntity>> getComments(long photoId);
}
