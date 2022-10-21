package com.example.room.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.room.adapter.DataAdapter;
import com.example.room.dao.Car;
import com.example.room.databinding.ActivityMainBinding;
import com.example.room.room.Data;
import com.example.room.room.RoomDB;
import com.example.room.util.LogUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainModel mainModel;
    private DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        sort();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        binding.recyclerList.setLayoutManager(new LinearLayoutManager(this));

        mainModel = new MainModel(this);

        mainModel.addList();
    }

    public void connectAdapter(ArrayList<Data> carList){
        adapter = new DataAdapter(this, carList);
        binding.recyclerList.setAdapter(adapter);
    }

    private void sort(){
        binding.btnSort.setOnClickListener(v->{
            mainModel.sortData();
        });
    }
}