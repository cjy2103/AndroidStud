package com.example.searchview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.searchview.adapter.RecyclerItemAdapter;
import com.example.searchview.databinding.ActivityMainBinding;
import com.example.searchview.model.MainModel;

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
    }

    public void recyclerViewConnection(RecyclerItemAdapter adapter){
        binding.recyclerItem.setAdapter(adapter);
    }

    private void searchClick(){
        binding.edtInput.setOnClickListener(v->{
            mainModel.moveSearch();
        });
    }

    public void callbackSearch(String word){
        binding.edtInput.setText(word);
    }
}