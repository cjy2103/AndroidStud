package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.sqlite.adapter.DataAdapter;
import com.example.sqlite.databinding.ActivityMainBinding;
import com.example.sqlite.db.DBHelper;
import com.example.sqlite.util.LogUtil;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SQLiteDatabase database;
    private DBHelper dbHelper;

    private DataAdapter adapter;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        insertData();
        deleteData();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        dbHelper = DBHelper.getInstance(this);
        database = dbHelper.getWritableDatabase();
        dbHelper.onCreate(database);
        database.close();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        refreshList();
    }

    private void insertData(){
        binding.btnInsert.setOnClickListener(v->{
            dbHelper.insertData("스포티지");
            dbHelper.insertData("토레스");
            dbHelper.insertData("투싼");
            refreshList();
        });

    }

    private void deleteData(){
        binding.btnDelete.setOnClickListener(v->{
            dbHelper.deleteData();
            refreshList();
        });
    }

    private void refreshList(){
        list = dbHelper.getName();
        adapter = new DataAdapter(this,list);
        binding.recyclerView.setAdapter(adapter);
    }
}