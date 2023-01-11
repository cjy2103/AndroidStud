package com.example.mvvm.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.Character;
import com.example.mvvm.model.CharacterProvider;

import java.util.ArrayList;

public class ImageViewModel extends ViewModel {

    private MutableLiveData<Character> characterData;
    private ArrayList<Character>  characters   = new CharacterProvider().getCharacters();
    private int                        currentIndex = 0;

    public MutableLiveData<Character> getCharacterData() {
        if(characterData == null){
            characterData = new MutableLiveData<>();
            characterData.setValue(characters.get(currentIndex));
        }
        return characterData;
    }

    public void nextCharacter(){
        currentIndex++;
        currentIndex %= characters.size();

        characterData.setValue(characters.get(currentIndex));
    }
}
