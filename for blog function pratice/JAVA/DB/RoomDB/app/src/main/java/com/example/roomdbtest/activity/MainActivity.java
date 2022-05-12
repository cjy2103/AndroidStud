package com.example.roomdbtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.roomdbtest.R;
import com.example.roomdbtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}