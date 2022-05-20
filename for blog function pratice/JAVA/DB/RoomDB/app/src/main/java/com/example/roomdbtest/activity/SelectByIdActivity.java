package com.example.roomdbtest.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.roomdbtest.databinding.ActivitySelectByIdBinding;
import com.example.roomdbtest.room.Data;
import com.example.roomdbtest.room.RoomDB;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SelectByIdActivity extends AppCompatActivity {

    private ActivitySelectByIdBinding binding;

    private RoomDB roomDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        btnSearch();
    }

    private void viewBinding(){
        binding = ActivitySelectByIdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        roomDB = RoomDB.getInstance(this);
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void btnSearch(){
        binding.btnSearch.setOnClickListener(v->{
            roomDB.dataDao().loadById(binding.edtTitle.getText().toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {
                        if(item.size() > 0) {
                            String title = item.get(0).getTitle();
                            String msg = item.get(0).getMsg();
                            binding.tvResult.setText("제목 : " + title + "\n메시지 : "+msg);
                        } else {
                            binding.tvResult.setText("데이터 없음");
                        }
                    });
        });
    }
}