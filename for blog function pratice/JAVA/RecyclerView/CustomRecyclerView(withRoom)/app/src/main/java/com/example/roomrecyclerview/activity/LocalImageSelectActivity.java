package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityLocalImageSelectBinding;

public class LocalImageSelectActivity extends AppCompatActivity {

    private ActivityLocalImageSelectBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
    }

    private void viewBinding(){
        binding = ActivityLocalImageSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


}