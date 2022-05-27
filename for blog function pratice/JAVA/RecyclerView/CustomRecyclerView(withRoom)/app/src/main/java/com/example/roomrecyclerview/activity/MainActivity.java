package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityMainBinding;
import com.example.roomrecyclerview.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();


        Typeface tfMapleLight = getResources().getFont(R.font.font_maple_change);
        binding.tvTitle.setTypeface(tfMapleLight);

        clickInsert();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickInsert(){
        binding.btnInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

}