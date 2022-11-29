package com.example.arraylistsharedpreference.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.arraylistsharedpreference.activity.MainActivity;
import com.example.arraylistsharedpreference.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivityModel {

    private ArrayList<String> dataList = new ArrayList<>();

    private final MainActivity mainActivity;

    public MainActivityModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void dataLoad(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString(key, null);

        if (json != null) {
            try {
                JSONArray jsonArray = new JSONArray(json);

                for(int i=0;i<jsonArray.length();i++){
                    String data = jsonArray.getString(i);
                    dataList.add(data);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        mainActivity.tvDataLoad(dataList.toString());
    }
}
