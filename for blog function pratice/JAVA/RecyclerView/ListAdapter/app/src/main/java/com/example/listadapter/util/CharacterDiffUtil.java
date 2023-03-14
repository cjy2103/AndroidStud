package com.example.listadapter.util;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.listadapter.model.Character;

public class CharacterDiffUtil extends DiffUtil.ItemCallback<Character> {
    @Override
    public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
        return oldItem.getTitle() == newItem.getTitle();
    }


    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
        return oldItem == newItem;
    }
}
