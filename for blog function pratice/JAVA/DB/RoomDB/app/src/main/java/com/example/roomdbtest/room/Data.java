package com.example.roomdbtest.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey
    @NonNull
    private String title;
    @ColumnInfo
    private String msg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String toString(){
        return "메시지 : {" +
                "title =" + title +
                "subTitle =" + msg + '\'' +
                '}';
    }
}
