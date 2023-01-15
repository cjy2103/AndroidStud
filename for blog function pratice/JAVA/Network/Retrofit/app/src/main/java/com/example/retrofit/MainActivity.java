package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.example.retrofit.databinding.ActivityMainBinding;
import com.example.retrofit.vm.FoodViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FoodViewModel foodViewModel = new FoodViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
    }

    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setViewModel(foodViewModel);
    }
}