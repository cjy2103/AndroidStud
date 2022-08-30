package com.example.roomrecyclerview.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Data.class}, version = 2, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static volatile RoomDB INSTANCE = null;
    private static String DATABASE_NAME = "Room";

    public abstract DataDao dataDao();

    private static final Migration MIGRATION_1_2  =  new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE 'Data' ADD COLUMN 'addColumn' TEXT");
        }
    };

    public static RoomDB getInstance(Context context){
        if(INSTANCE == null){
            synchronized (RoomDB.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , RoomDB.class, DATABASE_NAME)
//                            .fallbackToDestructiveMigration()
                            .addMigrations(MIGRATION_1_2)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
