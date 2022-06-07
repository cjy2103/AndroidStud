package com.example.roomrecyclerview.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface DataDao {
    @Query("SELECT * FROM Data")
    Single<List<Data>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Data... data);

    @Update
    Completable update(Data... data);

}
