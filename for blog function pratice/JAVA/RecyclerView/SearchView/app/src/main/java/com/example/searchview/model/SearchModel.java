package com.example.searchview.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.inputmethod.EditorInfo;

import com.example.searchview.activity.SearchActivity;

import org.json.JSONArray;

import java.util.LinkedList;

public class SearchModel {

    private SearchActivity searchActivity;
    private Context context;
    private Activity activity;
    private SharedPreferences preferences;

    private LinkedList<String> recentList = new LinkedList<>();
    private int                maxSize    = 10; // 최대 저장 개수

    public SearchModel(SearchActivity searchActivity, Context context) {
        this.searchActivity = searchActivity;
        this.context = context;
        activity = (Activity) context;
    }

    public void sendWord(String word, int actionId){
        preferences = context.getSharedPreferences("RecentSearchList",Context.MODE_PRIVATE);
        if(EditorInfo.IME_ACTION_SEARCH == actionId){
            arraySave(word);
            activity.finish();
        }
    }

    private void arraySave(String word){

        if(recentList.size() < maxSize && word.length() > 0){
            recentList.removeIf(element -> element.equals(word));
            recentList.add(word);
        } else {
            if(word.length() > 0){
                recentList.removeIf(element -> element.equals(word));
                recentList.add(word);
            } else {
                recentList.remove(0);
                recentList.add(word);
            }
        }
        recentDataSave();
    }

    private void recentDataSave(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i< this.recentList.size(); i++){
            jsonArray.put(this.recentList.get(i));
        }

        if(!this.recentList.isEmpty()){
            editor.putString("recentList",jsonArray.toString());
        } else {
            editor.putString("recentList", null);
        }
        editor.apply();
    }
}
