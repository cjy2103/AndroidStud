package com.example.mvvm.model;

public class Character {
    private String name;
    private String image;

    public Character(String name, String image) {
        this.name  = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
