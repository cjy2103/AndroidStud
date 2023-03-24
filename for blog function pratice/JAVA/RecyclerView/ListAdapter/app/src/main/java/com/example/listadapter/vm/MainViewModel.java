package com.example.listadapter.vm;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;


import com.example.listadapter.adapter.CharacterAdapter;
import com.example.listadapter.model.Character;
import com.example.listadapter.model.CharacterProvider;

import java.util.ArrayList;
import java.util.Objects;


public class MainViewModel {

    private CharacterProvider provider = new CharacterProvider();
    private MutableLiveData<ArrayList<Character>> characterList = provider.getCharacterList();

    public MutableLiveData<ArrayList<Character>> getCharacterList() {
        return characterList;
    }

    public void itemAdd(){
        provider.clickBtnAdd();
        characterList.setValue(provider.getCharacterList().getValue());
    }

    public void deleteItem(){
        provider.deleteItem();
        characterList.setValue(provider.getCharacterList().getValue());
    }


}
