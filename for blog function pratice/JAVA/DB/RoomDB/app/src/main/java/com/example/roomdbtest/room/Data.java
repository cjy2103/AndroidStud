package com.example.roomdbtest.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey
    private int key;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String subTItle;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTItle() {
        return subTItle;
    }

    public void setSubTItle(String subTItle) {
        this.subTItle = subTItle;
    }

    public String toString(){
        return "메시지 : {" +
                "key =" + key +
                "title =" + title +
                "subTitle =" + subTItle + '\'' +
                '}';
    }
}
