package com.example.roomtest.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {USER.class}, version = 1, exportSchema = false)
public abstract class UserDB extends RoomDatabase {
    private static UserDB INSTANCE = null;
    private static String DATABASE_NAME = "ROOM_TEST";

    public abstract UserDao userDao();

    public static UserDB getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), UserDB.class, DATABASE_NAME).build();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }
}
