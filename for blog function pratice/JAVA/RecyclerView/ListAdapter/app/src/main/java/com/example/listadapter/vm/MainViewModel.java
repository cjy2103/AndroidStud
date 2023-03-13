package com.example.listadapter.vm;

import android.content.Context;
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

    private CharacterAdapter adapter;

    public CharacterAdapter getAdapter() {
        return adapter;
    }

    public MainViewModel() {
        adapter = new CharacterAdapter();
        adapter.setList(new ArrayList<>(Objects.requireNonNull(characterList.getValue())));
    }

    public void itemAdd(View view){
        provider.clickBtnAdd();
        adapter.setList(new ArrayList<>(Objects.requireNonNull(characterList.getValue())));
    }

    public void deleteItem(View view){
        provider.deleteItem();
        adapter.setList(new ArrayList<>(Objects.requireNonNull(characterList.getValue())));
    }


}
