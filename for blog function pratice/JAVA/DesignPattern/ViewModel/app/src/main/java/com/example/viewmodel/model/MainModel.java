package com.example.viewmodel.model;

import android.content.Context;
import android.net.Uri;

import com.example.viewmodel.R;

import java.util.ArrayList;

public class MainModel {

    private final Context context;

    private final ArrayList<String> imageList = new ArrayList<>();
    private final ArrayList<String> nameList  = new ArrayList<>();
    String imageUri = "drawable://";

    public MainModel(Context context) {
        this.context      = context;
        init();
    }

    private void init(){
        imageList.add(imageUri + R.drawable.baknana);
        imageList.add(imageUri + R.drawable.mwama);
        imageList.add(imageUri + R.drawable.tamtam);

        nameList.add(context.getResources().getString(R.string.baknana));
        nameList.add(context.getResources().getString(R.string.mwamwa));
        nameList.add(context.getResources().getString(R.string.tamtam));
    }

    public Uri getImagePath(int num){
        int pos   = num % 3;
        return Uri.parse("android.resource://" + context.getPackageName() + "/" + imageList.get(pos));
    }

    public String getTitle(int num){
        int pos   = num % 3;
        return nameList.get(pos);
    }



}
