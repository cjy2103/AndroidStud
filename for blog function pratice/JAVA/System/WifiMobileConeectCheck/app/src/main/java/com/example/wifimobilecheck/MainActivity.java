package com.example.wifimobilecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.net.NetworkRequest;
import android.os.Bundle;

import com.example.wifimobilecheck.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        stateCheck();
    }

    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void stateCheck(){
        binding.btnState.setOnClickListener(v->{

        });
    }

}