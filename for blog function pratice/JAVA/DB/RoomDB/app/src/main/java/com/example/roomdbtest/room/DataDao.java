package com.example.roomdbtest.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface DataDao {
    @Query("SELECT * FROM Data")
    Single<List<Data>> getAll();

    @Query("SELECT * FROM Data WHERE title = :title")
    Single<List<Data>> loadById(String title);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Data... data);

    @Query("DELETE FROM Data")
    Completable deleteAll();

}
