package com.example.listadapter.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return title == character.title && describe == character.describe && image == character.image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, describe, image);
    }
}
