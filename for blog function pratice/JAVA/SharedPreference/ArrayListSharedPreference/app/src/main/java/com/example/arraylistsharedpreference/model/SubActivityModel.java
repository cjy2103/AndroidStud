package com.example.arraylistsharedpreference.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.arraylistsharedpreference.util.LogUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class SubActivityModel {

    private ArrayList<String> dataList = new ArrayList<>(Arrays.asList("봄","여름","가을","겨울"));

    public void dataSave(Context context, String key){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();
        for(int i=0;i<dataList.size();i++){
            jsonArray.put(dataList.get(i));
        }

        if(!dataList.isEmpty()){
            editor.putString(key, jsonArray.toString());
        } else {
            editor.putString(key, null);
        }

        editor.apply();

        Toast.makeText(context, "데이터 저장완료",Toast.LENGTH_SHORT).show();
    }
}
