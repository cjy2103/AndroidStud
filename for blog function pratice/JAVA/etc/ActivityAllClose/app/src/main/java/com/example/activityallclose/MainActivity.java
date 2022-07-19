package com.example.activityallclose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.activityallclose.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        openActivity();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void openActivity(){
        binding.btnOpen.setOnClickListener(v->{
            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("number",1);
            startActivity(intent);
        });
    }
}