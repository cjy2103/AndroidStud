package com.example.mvvm.model;

public class Character {
    private final String name;
    private final int    image;

    public Character(String name, int image) {
        this.name  = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }
}
