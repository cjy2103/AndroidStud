package com.example.room.activity;

import android.content.Context;

import com.example.room.room.Data;
import com.example.room.room.RoomDB;

import java.util.ArrayList;

public class MainModel {
    private RoomDB roomDB;
    private ArrayList<Data> dataList;
    private MainActivity mainActivity;
    private Data data;
    private Context context;

    public MainModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;


    }

    public void dataListAdd(){

    }


}
