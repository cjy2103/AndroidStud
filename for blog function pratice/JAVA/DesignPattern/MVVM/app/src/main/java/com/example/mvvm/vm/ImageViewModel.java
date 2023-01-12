package com.example.mvvm.vm;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.mvvm.model.Character;
import com.example.mvvm.model.CharacterProvider;

import java.util.ArrayList;

public class ImageViewModel {

    private MutableLiveData<Character> characterData;

    private final ArrayList<Character> characters = new CharacterProvider().getCharacters();

    private int currentIndex = 0;

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

    @BindingAdapter("loadImage")
    public static void characterImage(ImageView imageView, int imageUrl){
        Glide.with(imageView.getContext())
                .load(imageUrl).into(imageView);
    }
}
