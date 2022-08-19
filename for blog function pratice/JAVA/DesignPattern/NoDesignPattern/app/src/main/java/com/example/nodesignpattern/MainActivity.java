package com.example.nodesignpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.nodesignpattern.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        binding.btnPick.setOnClickListener(v->{

        });
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}