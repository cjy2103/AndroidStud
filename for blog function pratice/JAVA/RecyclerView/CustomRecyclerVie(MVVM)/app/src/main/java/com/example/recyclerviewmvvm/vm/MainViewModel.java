package com.example.recyclerviewmvvm.vm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.recyclerviewmvvm.adapter.CharacterAdapter;
import com.example.recyclerviewmvvm.model.Character;
import com.example.recyclerviewmvvm.model.CharacterProvider;

import java.util.ArrayList;

public class MainViewModel {
    private MutableLiveData<ArrayList<Character>> characterList = new CharacterProvider().getCharacterList();
    private CharacterAdapter adapter;

    public CharacterAdapter getAdapter() {
        return adapter;
    }

    public MainViewModel() {
        adapter = new CharacterAdapter( characterList.getValue());
    }


}
