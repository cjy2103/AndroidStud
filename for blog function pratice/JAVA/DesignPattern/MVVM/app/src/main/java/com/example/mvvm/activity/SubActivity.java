package com.example.mvvm.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvvm.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding();
    }

    private void viewbinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}