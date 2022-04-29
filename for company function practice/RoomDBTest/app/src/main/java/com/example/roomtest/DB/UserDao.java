package com.example.roomtest.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM USER")
    Flowable<List<USER>> getAll();

    @Query("SELECT * FROM user WHERE uid = :userIds")
    Single<List<USER>> loadById(int userIds);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(USER... users);

    @Query("DELETE FROM USER")
    Completable delete();

    @Query("UPDATE user SET age =:userAge  WHERE uid =:userIds")
    Completable updateById(int userAge, int userIds);



}
