package com.example.recyclerviewmvvm.model;

public class Character {
    private int title;
    private int describe;
    private int image;

    public Character(int title, int describe, int image) {
        this.title    = title;
        this.describe = describe;
        this.image    = image;
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
