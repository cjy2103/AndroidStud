package com.example.arraylistsharedpreference.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.arraylistsharedpreference.databinding.ActivitySubBinding;
import com.example.arraylistsharedpreference.model.SubActivityModel;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    private SubActivityModel subActivityModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        dataSave();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        subActivityModel = new SubActivityModel();
    }

    private void dataSave(){
        binding.btnDataSave.setOnClickListener(v->{
            subActivityModel.dataSave(this,"ListData");
        });
    }
}