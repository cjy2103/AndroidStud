package com.example.roomdbtest.room;

import androidx.room.Query;

import java.util.List;

import io.reactivex.Single;

public interface DataDao {
    @Query("SELECT * FROM DATA")
    Single<List<DATA>> getAll();
}
