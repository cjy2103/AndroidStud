package com.example.recyclerviewmvvm.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewmvvm.model.Character;

import java.util.ArrayList;

public class MyBindingAdapter {
    @BindingAdapter("setCharacter")
    public static void setItems(RecyclerView recyclerView, ArrayList<Character> list){
        if(recyclerView.getAdapter() == null) {
            CharacterAdapter adapter = new CharacterAdapter(list);
            recyclerView.setAdapter(adapter);
        }
    }

    @BindingAdapter("app:srcCompat")
    public static void setImage(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }
}
