package com.example.mvvm.model;

import com.example.mvvm.R;

import java.util.ArrayList;

public class CharacterProvider {

    private ArrayList<Character> characters = new ArrayList<>();

    public CharacterProvider() {
        characters.add(new Character("박나나",R.drawable.baknana));
        characters.add(new Character("클리어,페일",R.drawable.djmax_clear_fail));
        characters.add(new Character("FallingLove",R.drawable.djmax_falling_in_love));
        characters.add(new Character("뫄뫄",R.drawable.mwama));
        characters.add(new Character("탬탬버린", R.drawable.tamtam));
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }


}
