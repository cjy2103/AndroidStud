package com.example.searchview.model;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.searchview.R;
import com.example.searchview.activity.MainActivity;
import com.example.searchview.activity.SearchActivity;
import com.example.searchview.adapter.RecyclerItemAdapter;
import com.example.searchview.dao.ListItem;
import com.example.searchview.util.LogUtil;

import java.util.ArrayList;

public class MainModel {

    private MainActivity mainActivity;
    private ArrayList<ListItem> originList;
    private ArrayList<ListItem> searchList;
    private Context context;

    private RecyclerItemAdapter adapter;

    private ActivityResultLauncher<Intent> resultLauncher;

    public MainModel(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context = context;
        originList = new ArrayList<>();
        searchCallback();
    }

    private void searchCallback(){
        resultLauncher = mainActivity.registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result ->{
           if(result.getResultCode() == Activity.RESULT_OK){
               if(result.getData() == null){
                   return;
               }
               String word = result.getData().getExtras().getString("word");
               listFilter(word);
               mainActivity.callbackSearch(word);
           }
        });
    }

    public void listAdd(){
        String imageUri = "drawable://";
        addItem(context.getResources().getString(R.string.baknana),context.getResources().getString(R.string.bak_describe)
                ,imageUri + R.drawable.baknana, context.getResources().getString(R.string.baknana_link));
        addItem(context.getResources().getString(R.string.djmax),context.getResources().getString(R.string.djmax_describe)
                ,imageUri + R.drawable.djmax_clear_fail, context.getResources().getString(R.string.djmax_archive));
        addItem(context.getResources().getString(R.string.djmax_falling_love), context.getResources().getString(R.string.djmax_falling_love_describe)
                ,imageUri + R.drawable.djmax_falling_in_love, context.getResources().getString(R.string.djmax_falling_love_link));
        addItem(context.getResources().getString(R.string.mwamwa), context.getResources().getString(R.string.mwamwa_describe)
                ,imageUri + R.drawable.mwama, context.getResources().getString(R.string.mwamwa_link));
        addItem(context.getResources().getString(R.string.tamtam), context.getResources().getString(R.string.mwamwa_describe)
                ,imageUri + R.drawable.tamtam, context.getResources().getString(R.string.tamtam_link));

        adapterSetting();
    }

    private void addItem(String title, String describe, String path, String link){
        ListItem listItemModel = new ListItem();
        listItemModel.setTitle(title);
        listItemModel.setDescribe(describe);
        listItemModel.setUri(path);
        listItemModel.setChannelLink(link);

        originList.add(listItemModel);
    }

    private void adapterSetting(){
        adapter = new RecyclerItemAdapter(context, originList);
        mainActivity.recyclerViewConnection(adapter);
    }

    public void moveSearch(){
        Intent intent = new Intent(context, SearchActivity.class);
        resultLauncher.launch(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void listFilter(String word){
        searchList = new ArrayList<>();
        for(ListItem item : originList){
            if(item.getTitle().contains(word)){
                searchList.add(item);
            }
        }

        adapter.setFilterList(searchList);
        adapter.notifyDataSetChanged();

    }

}
