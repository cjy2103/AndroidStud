package com.example.sqlite.db;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.sqlite.util.LogUtil;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static volatile DBHelper INSTANCE = null;

    public static final String DATABASE = "SQLite";
    public static SQLiteDatabase db;

    public static DBHelper getInstance(Context context){
        if(INSTANCE == null){
            synchronized (DBHelper.class){
                if(INSTANCE == null){
                    INSTANCE = new DBHelper(context,null,1);
                }
            }
        }
        return INSTANCE;
    }

    public DBHelper(Context context,@Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE, factory, version);
        try{
            db = context.openOrCreateDatabase(DATABASE, Activity.MODE_PRIVATE, null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Data (number INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)");
    }

    /**
     * @DESC: DB의 버전이 바뀌면 실행이됨
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Data");
        onCreate(db);
    }

    public void insertData(String name){
        db = getWritableDatabase();
        db.execSQL("INSERT INTO Data(name) VALUES(?)",
                new String[]{name});
        db.close();
    }

    public ArrayList<String> getName(){
        ArrayList<String> list = new ArrayList<>();

        db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM Data",null);

        while (cursor.moveToNext()){
            list.add(cursor.getString(1));
        }

        cursor.close();
        db.close();

        return list;
    }

    public void deleteData(){
        db = getWritableDatabase();
        db.execSQL("DELETE FROM Data");
        db.close();
    }
}
