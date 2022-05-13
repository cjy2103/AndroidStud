package com.example.roomdbtest.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {RoomDB.class}, version = 1, exportSchema = false)
public class RoomDB extends RoomDatabase {

}
