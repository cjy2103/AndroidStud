package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.databinding.ActivityPreViewBinding;

public class PreViewActivity extends AppCompatActivity {

    private ActivityPreViewBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        clickOk();
    }

    private void viewBinding(){
        binding = ActivityPreViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        String imagePath = getIntent().getStringExtra("photoUri");
        if(imagePath!=null) {
            Uri photoUri = Uri.parse(imagePath);
            Glide.with(this).load(photoUri).into(binding.ivPhoto);
        }
    }

    private void clickOk(){
        binding.btnOk.setOnClickListener(v->{
            finish();
        });
    }
}