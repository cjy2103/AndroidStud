package com.example.roomdbtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.roomdbtest.activity.adapter.SelectAllAdapter;
import com.example.roomdbtest.activity.model.DataModel;
import com.example.roomdbtest.databinding.ActivitySelectAllBinding;
import com.example.roomdbtest.room.Data;
import com.example.roomdbtest.room.RoomDB;
import com.example.roomdbtest.util.LogUtils;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectAllActivity extends AppCompatActivity {

    private ActivitySelectAllBinding binding;

    private RoomDB roomDB = null;
    private ArrayList<DataModel> dataList;
    private SelectAllAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding();

        initialize();

        dataLoad();
    }

    private void viewBinding(){
        binding = ActivitySelectAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        roomDB = RoomDB.getInstance(this);

        dataList = new ArrayList<>();


        binding.recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    @SuppressLint("CheckResult")
    private void dataLoad(){
        roomDB.dataDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( item ->{
                   for(Data data : item){
                        dataListAdd(data);
                   }
                   recyclerConnection();
                });
    }

    private void dataListAdd(Data data){
        DataModel dataModel = new DataModel();
        dataModel.setTitle(data.getTitle());
        dataModel.setMsg(data.getMsg());

        dataList.add(dataModel);
    }

    private void recyclerConnection(){
        adapter = new SelectAllAdapter(this, dataList);
        binding.recyclerList.setAdapter(adapter);
    }

}