package com.example.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvm.R;
import com.example.mvvm.vm.ImageViewModel;
import com.example.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageViewModel imageViewModel = new ImageViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
    }

    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(imageViewModel);
        binding.setLifecycleOwner(this);
    }
}