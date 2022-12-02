package com.example.searchview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.searchview.adapter.RecyclerItemAdapter;
import com.example.searchview.dao.ListItem;
import com.example.searchview.databinding.ActivityMainBinding;
import com.example.searchview.model.MainModel;
import com.example.searchview.util.SystemUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        searchClick();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainModel = new MainModel(this,this);
        mainModel.listAdd();
        binding.recyclerItem.setLayoutManager(new LinearLayoutManager(this));
        SystemUtil.sofNavigationBarHide(getWindow());
        SystemUtil.statusbarSetting(getWindow(),this,binding.consMain);
    }

    public void recyclerViewConnection(RecyclerItemAdapter adapter){
        binding.recyclerItem.setAdapter(adapter);
    }

    private void searchClick(){
        binding.consSearch.setOnClickListener(v->{
            mainModel.moveSearch();
        });
    }

    public void callbackSearch(String word){
        binding.tvWord.setText(word);
    }
}