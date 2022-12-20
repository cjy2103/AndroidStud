package com.example.roomrecyclerview.room;

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
    private String describe;
    @ColumnInfo
    private String youtubeLink;
    @ColumnInfo
    private String imageKey;
    @ColumnInfo
    private String imageCase;
    @ColumnInfo
    private String addColumn;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }

    public String getImageCase() {
        return imageCase;
    }

    public void setImageCase(String imageCase) {
        this.imageCase = imageCase;
    }

    public String getAddColumn() {
        return addColumn;
    }

    public void setAddColumn(String addColumn) {
        this.addColumn = addColumn;
    }

    @NonNull
    public String toString(){
        return "메시지 : {" +
                "title =" + title +
                "describe =" + describe +
                "youtubeLink =" + youtubeLink +
                "imagePath =" + imageKey +
                "imageCase =" + imageCase + '\'' +
                '}';
    }
}
