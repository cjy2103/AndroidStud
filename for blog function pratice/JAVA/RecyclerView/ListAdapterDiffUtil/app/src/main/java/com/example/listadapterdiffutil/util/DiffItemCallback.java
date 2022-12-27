package com.example.listadapterdiffutil.util;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.listadapterdiffutil.dto.ListItem;

import java.util.Objects;

public class DiffItemCallback extends DiffUtil.ItemCallback<ListItem> {

    @Override
    public boolean areItemsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        LogUtil.log("과거"+oldItem.getTitle());
        LogUtil.log("현재"+newItem.getTitle());
        LogUtil.log(String.valueOf(Objects.equals(oldItem.getTitle(), newItem.getTitle())));
        return Objects.equals(oldItem.getTitle(), newItem.getTitle());
    }

    @SuppressLint("DiffUtilEquals")
    @Override
    public boolean areContentsTheSame(@NonNull ListItem oldItem, @NonNull ListItem newItem) {
        return oldItem == newItem;
    }
}
