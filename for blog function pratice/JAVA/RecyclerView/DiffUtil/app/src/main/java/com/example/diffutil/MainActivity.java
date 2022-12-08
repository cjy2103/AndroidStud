package com.example.diffutil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.diffutil.adapter.ListItemAdapter;
import com.example.diffutil.databinding.ActivityMainBinding;
import com.example.diffutil.model.MainModel;

public class MainActivity extends AppCompatActivity{

    private ActivityMainBinding binding;
    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        listAdd();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainModel = new MainModel(this,this);
    }

    private void listAdd(){
        binding.btnAdd.setOnClickListener(v->{
            mainModel.listAdd();
        });
    }

    public void setRecycler(ListItemAdapter adapter){
        binding.recyclerList.setAdapter(adapter);
    }

}