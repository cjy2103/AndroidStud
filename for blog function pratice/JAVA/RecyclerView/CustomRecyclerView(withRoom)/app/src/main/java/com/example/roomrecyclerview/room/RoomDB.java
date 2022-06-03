package com.example.roomrecyclerview.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static volatile RoomDB INSTANCE = null;
    private static String DATABASE_NAME = "Room";

    public static RoomDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (RoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDB.class, DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

}
