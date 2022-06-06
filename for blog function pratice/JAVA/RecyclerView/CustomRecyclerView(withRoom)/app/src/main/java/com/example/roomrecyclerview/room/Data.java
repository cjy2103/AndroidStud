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
    private String imagePath;
    @ColumnInfo
    private String imageCase;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageCase() {
        return imageCase;
    }

    public void setImageCase(String imageCase) {
        this.imageCase = imageCase;
    }
}
