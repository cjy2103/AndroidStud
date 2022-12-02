package com.example.searchview.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.inputmethod.EditorInfo;

import com.example.searchview.activity.SearchActivity;
import com.example.searchview.adapter.SearchHistoryAdapter;
import com.example.searchview.util.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.LinkedList;

public class SearchModel {

    private SearchActivity searchActivity;
    private Context context;
    private Activity activity;
    private SharedPreferences preferences;

    private LinkedList<String> recentList = new LinkedList<>();
    private int                maxSize    = 10; // 최대 저장 개수

    private SearchHistoryAdapter adapter;

    public SearchModel(SearchActivity searchActivity, Context context) {
        this.searchActivity = searchActivity;
        this.context = context;
        activity = (Activity) context;
    }

    public void loadHistory(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        String json = prefs.getString("recentList",null);

        if(json != null){
            try{
                JSONArray jsonArray = new JSONArray(json);

                for(int i=0;i<jsonArray.length();i++){
                    String word = jsonArray.getString(i);
                    recentList.add(word);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        adapterSetting();

    }

    private void adapterSetting(){
        adapter = new SearchHistoryAdapter(context, recentList);
        if(recentList.size() > 0){
            searchActivity.hideText();
        }
        searchActivity.recyclerViewConnection(adapter);

        itemClick();
        deleteItem();
    }

    private void itemClick(){
        adapter.setOnItemClickListener((v,position) -> {
            String word = recentList.get(position);
            arraySave(word);
            Intent intent = new Intent();
            intent.putExtra("word",word);
            activity.setResult(Activity.RESULT_OK, intent);

            activity.finish();
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteItem(){
        adapter.setDeleteListener((v, position) -> {
            recentList.remove(position);
            recentDataSave();
            adapter.setList(recentList);
            adapter.notifyDataSetChanged();
            if(recentList.size() ==  0){
                searchActivity.showText();
            }
        });
    }


    public void sendWord(String word, int actionId){
        preferences = context.getSharedPreferences("RecentSearchList",Context.MODE_PRIVATE);
        if(EditorInfo.IME_ACTION_SEARCH == actionId){
            arraySave(word);
            Intent intent = new Intent();
            intent.putExtra("word",word);
            activity.setResult(Activity.RESULT_OK, intent);

            activity.finish();
        }
    }

    private void arraySave(String word){

        if(recentList.size() < maxSize && word.length() > 0){
            recentList.removeIf(element -> element.equals(word));
            recentList.addFirst(word);
        } else {
            if(word.length() > 0){
                recentList.removeIf(element -> element.equals(word));
                recentList.addFirst(word);
            } else {
                recentList.remove(0);
                recentList.addFirst(word);
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

    @SuppressLint("NotifyDataSetChanged")
    public void deleteAll(){
        recentList.clear();
        recentDataSave();
        adapter.setList(recentList);
        adapter.notifyDataSetChanged();
        searchActivity.showText();
    }

}
