package com.example.customlistview.listview;

import android.graphics.drawable.Drawable;

public class ListViewItem {
    private Drawable image;
    private String title;
    private String describe;



    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
