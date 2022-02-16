package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import com.example.recyclerview.adapter.CustomRecyclerAdatper;
import com.example.recyclerview.databinding.ActivityMainBinding;
import com.example.recyclerview.model.ListItemModel;
import com.example.recyclerview.model.MyListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<MyListItem> myListItems;
    private CustomRecyclerAdatper adatper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        listadd();

        adatper = new CustomRecyclerAdatper(this,this,myListItems);
        binding.recyclerList.setAdapter(adatper);


    }

    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        myListItems = new ArrayList<>();
        binding.recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    private void listadd(){
        String imageUri = "drawable://";
        addItem(getResources().getString(R.string.baknana),getResources().getString(R.string.bak_describe),imageUri + R.drawable.baknana);
        addItem(getResources().getString(R.string.djmax),getResources().getString(R.string.djmax_describe),imageUri + R.drawable.djmax_clear_fail);
        addItem(getResources().getString(R.string.djmax_falling_love),getResources().getString(R.string.djmax_falling_love_describe),imageUri + R.drawable.djmax_falling_in_love);
        addItem(getResources().getString(R.string.mwamwa),getResources().getString(R.string.mwamwa_describe),imageUri + R.drawable.mwama);
        addItem(getResources().getString(R.string.tamtam),getResources().getString(R.string.mwamwa_describe),imageUri + R.drawable.tamtam);
    }

    private void addItem(String title, String describe, String uri){
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setTitle(title);
        listItemModel.setDescribe(describe);
        listItemModel.setUri(uri);

        MyListItem myListItem = new MyListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        myListItem.setList(items);

        myListItems.add(myListItem);
    }
}