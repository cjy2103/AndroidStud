package com.example.listadapterdiffutil.model;

import android.content.Context;

import com.example.listadapterdiffutil.MainActivity;
import com.example.listadapterdiffutil.R;
import com.example.listadapterdiffutil.adapter.ListItemAdapter;
import com.example.listadapterdiffutil.dto.ListItem;
import com.example.listadapterdiffutil.util.LogUtil;

import java.util.ArrayList;

public class MainModel {

    private MainActivity mainActivity;
    private Context      context;

    private ArrayList<ListItem> list = new ArrayList<>();
    private ListItemAdapter     adapter;

    String imageUri = "drawable://";

    public MainModel(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context      = context;

        init();
    }

    private void init() {
        itemAdd(context.getResources().getString(R.string.baknana), context.getResources().getString(R.string.bak_describe),
                imageUri + R.drawable.baknana);
        itemAdd(context.getResources().getString(R.string.djmax), context.getResources().getString(R.string.djmax_describe),
                imageUri + R.drawable.djmax_clear_fail);
        itemAdd(context.getResources().getString(R.string.djmax_falling_love), context.getResources().getString(R.string.djmax_falling_love_describe),
                imageUri + R.drawable.djmax_falling_in_love);

        adapter = new ListItemAdapter(context, new ArrayList<>(list));

        mainActivity.setRecycler(adapter);
    }

    private void itemAdd(String title, String describe, String link) {
        ListItem listItem = new ListItem();
        listItem.setTitle(title);
        listItem.setDescribe(describe);
        listItem.setUrl(link);
        list.add(listItem);
    }

    public void listAdd() {
        itemAdd(context.getResources().getString(R.string.mwamwa), context.getResources().getString(R.string.mwamwa_describe),
                imageUri + R.drawable.mwama);
        itemAdd(context.getResources().getString(R.string.tamtam), context.getResources().getString(R.string.tamtam_describe),
                imageUri + R.drawable.tamtam);

        adapter.updateItem(new ArrayList<>(list));
    }
}


