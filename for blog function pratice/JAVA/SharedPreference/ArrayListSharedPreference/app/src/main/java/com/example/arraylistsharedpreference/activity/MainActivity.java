package com.example.arraylistsharedpreference.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.arraylistsharedpreference.databinding.ActivityMainBinding;
import com.example.arraylistsharedpreference.model.MainActivityModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainActivityModel mainActivityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        btnMove();
        btnDataLoad();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainActivityModel = new MainActivityModel(this);
    }

    private void btnMove(){
        binding.btnMove.setOnClickListener(v->{
            Intent intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });
    }

    private void btnDataLoad(){
        binding.btnLoadData.setOnClickListener(v->{
            mainActivityModel.dataLoad(this, "ListData");
        });
    }

    public void tvDataLoad(String list){
        binding.tvData.setText(list);
    }
}