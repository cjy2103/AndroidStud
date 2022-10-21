package com.example.room.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.room.dao.Car;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(Data data);

    @Query("SELECT * FROM Data ORDER BY CAST(PRICE AS SIGNED) ASC")
    Single<List<Data>>getSortAll();
}
