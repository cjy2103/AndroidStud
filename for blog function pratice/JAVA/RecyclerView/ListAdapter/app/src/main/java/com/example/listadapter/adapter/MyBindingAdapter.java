package com.example.listadapter.adapter;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadapter.model.Character;

import java.util.ArrayList;

public class MyBindingAdapter {
    @BindingAdapter("setCharacter")
    public static void setItems(RecyclerView recyclerView, ArrayList<Character> list){
        if(recyclerView.getAdapter() == null) {
            CharacterAdapter adapter = new CharacterAdapter();
            adapter.submitList(new ArrayList<>(list));
            recyclerView.setAdapter(adapter);
        } else {
            CharacterAdapter adapter = (CharacterAdapter) recyclerView.getAdapter();
            adapter.submitList(new ArrayList<>(list));
        }
    }

    @BindingAdapter("app:srcCompat")
    public static void setImage(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

}
