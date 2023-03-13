package com.example.listadapter.model;

import androidx.lifecycle.MutableLiveData;

import com.example.listadapter.R;

import java.util.ArrayList;

public class CharacterProvider {

    private ArrayList<Character> list = new ArrayList<>();

    private MutableLiveData<ArrayList<Character>> characterList = new MutableLiveData<>();

    public CharacterProvider() {
        addItem(R.string.baknana, R.string.bak_describe, R.drawable.baknana);
        addItem(R.string.djmax,R.string.djmax_describe, R.drawable.djmax_clear_fail);
        addItem(R.string.djmax_falling_love,R.string.djmax_falling_love_describe, R.drawable.djmax_falling_in_love);

//        addItem(R.string.mwamwa,R.string.mwamwa_describe, R.drawable.mwama);
//        addItem(R.string.tamtam, R.string.tamtam_describe,  R.drawable.tamtam);

        characterList.setValue(list);
    }

    private void addItem(int title, int describe, int image) {
        Character character = new Character(title,describe, image);
        if (!list.contains(character)) {
            list.add(character);
        }
    }

    public void clickBtnAdd(){
        addItem(R.string.mwamwa,R.string.mwamwa_describe, R.drawable.mwama);
        addItem(R.string.tamtam, R.string.tamtam_describe,  R.drawable.tamtam);
    }

    public void deleteItem(){
        if(list.size()>1) {
            for (int i = 0; i < 2; i++) {
                list.remove(list.size() - 1);
            }
        }
    }

    public MutableLiveData<ArrayList<Character>> getCharacterList() {
        return characterList;
    }
}
