package com.example.roomdbtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.roomdbtest.R;
import com.example.roomdbtest.databinding.ActivityMainBinding;
import com.example.roomdbtest.room.RoomDB;
import com.example.roomdbtest.util.LogUtils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private RoomDB roomDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        btnDataInsert();

        btnSelectAll();

        btnSelectById();

        btnDeleteAll();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        roomDB = RoomDB.getInstance(this);
    }

    private void btnDataInsert(){
        binding.btnInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

    private void btnSelectAll(){
        binding.btnAllSearch.setOnClickListener(v->{
            Intent intent = new Intent(this, SelectAllActivity.class);
            startActivity(intent);
        });
    }

    private void btnSelectById(){
        binding.btnSelectById.setOnClickListener(v->{
            Intent intent = new Intent(this, SelectByIdActivity.class);
            startActivity(intent);
        });
    }

    @SuppressLint("CheckResult")
    private void btnDeleteAll(){
        binding.btnAllDelete.setOnClickListener(v->{
            roomDB.dataDao().deleteAll().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(()->{
                        Toast.makeText(this,"데이터 전체 삭제 완료",Toast.LENGTH_SHORT).show();
                    });
        });
    }
}