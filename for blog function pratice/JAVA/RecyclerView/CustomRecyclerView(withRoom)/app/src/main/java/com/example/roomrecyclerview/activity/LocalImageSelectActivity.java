package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityLocalImageSelectBinding;

import java.util.ArrayList;

public class LocalImageSelectActivity extends AppCompatActivity {

    private ActivityLocalImageSelectBinding binding;
    private ArrayList<String> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();
    }

    private void viewBinding(){
        binding = ActivityLocalImageSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        imageList = new ArrayList<>();

        Typeface tfMapleLigth = getResources().getFont(R.font.maplestory_light);
        binding.tvTitle.setTypeface(tfMapleLigth);
    }



}