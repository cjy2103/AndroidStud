package com.example.listadapter.model;


public class Character {
    private int title;
    private int describe;
    private int image;

    public Character(int title, int describe, int image) {
        this.title    = title;
        this.describe = describe;
        this.image    = image;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public void setDescribe(int describe) {
        this.describe = describe;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public int getDescribe() {
        return describe;
    }

    public int getImage() {
        return image;
    }

}
