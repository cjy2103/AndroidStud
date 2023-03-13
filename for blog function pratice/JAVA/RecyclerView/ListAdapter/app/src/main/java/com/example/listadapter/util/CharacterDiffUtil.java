package com.example.listadapter.util;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.listadapter.model.Character;

public class CharacterDiffUtil extends DiffUtil.ItemCallback<Character> {
    @Override
    public boolean areItemsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
        return oldItem.equals(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull Character oldItem, @NonNull Character newItem) {
        return oldItem.equals(newItem);
    }
}
