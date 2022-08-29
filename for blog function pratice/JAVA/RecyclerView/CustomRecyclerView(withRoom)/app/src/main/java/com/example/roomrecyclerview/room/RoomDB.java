package com.example.roomrecyclerview.room;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Data.class}, version = 2, exportSchema = true, autoMigrations = {
        @AutoMigration(from = 1, to = 2)
})
public abstract class RoomDB extends RoomDatabase {
    private static volatile RoomDB INSTANCE = null;
    private static String DATABASE_NAME = "Room";

    public abstract DataDao dataDao();

    public static RoomDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (RoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , RoomDB.class, DATABASE_NAME).build();
                }
            }
        }
        return INSTANCE;
    }

}
