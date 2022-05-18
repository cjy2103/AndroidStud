package com.example.roomdbtest.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {RoomDB.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static String DATABASE_NAME = "ROOM";

    private static class InnerInstanceClass{
        private static RoomDB INSTANCE = null;
        public static RoomDB getInstance(Context context){
            
            return INSTANCE;
        }
    }


}
