package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.viewmodel.databinding.ActivityMainBinding;
import com.example.viewmodel.model.MainModel;
import com.example.viewmodel.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainModel mainModel;
    private MainViewModel mainViewModel;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        change();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mainViewModel.initLoad){
            Glide.with(this).load(mainViewModel.imagePath).into(binding.ivImage);
            binding.tvName.setText(mainViewModel.title);
            num = mainViewModel.num;
        }
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainModel = new MainModel(this);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    private void change(){
        binding.btnChange.setOnClickListener(v->{
            num++;
            Uri image = mainModel.getImagePath(num);
            String title = mainModel.getTitle(num);
            Glide.with(this).load(image).into(binding.ivImage);
            binding.tvName.setText(title);
            dataSave(image, title);
        });
    }

    private void dataSave(Uri image, String title){
        mainViewModel.imagePath = image;
        mainViewModel.title = title;
        mainViewModel.initLoad = false;
        mainViewModel.num = num;
    }
}