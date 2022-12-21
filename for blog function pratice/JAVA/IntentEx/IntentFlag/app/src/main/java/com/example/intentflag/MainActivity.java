package com.example.intentflag;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.intentflag.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        clickSub();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickSub(){
        binding.btnSub.setOnClickListener(v->{
            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("num",0);
            startActivity(intent);
        });
    }


}