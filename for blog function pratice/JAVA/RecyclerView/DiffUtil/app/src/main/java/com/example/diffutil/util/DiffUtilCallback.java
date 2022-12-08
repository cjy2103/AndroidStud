package com.example.diffutil.util;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import com.example.diffutil.dto.ListItem;

import java.util.ArrayList;

public class DiffUtilCallback extends DiffUtil.Callback {

    private ArrayList<ListItem> oldList;
    private ArrayList<ListItem> newList;

    public DiffUtilCallback(ArrayList<ListItem> oldList, ArrayList<ListItem> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getDescribe().equals(newList.get(newItemPosition).getDescribe());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
