package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityPhotoResultBinding;

public class PhotoResultActivity extends AppCompatActivity {

    private ActivityPhotoResultBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        clickOk();
    }

    private void viewBinding(){
        binding = ActivityPhotoResultBinding.inflate(getLayoutInflater());
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