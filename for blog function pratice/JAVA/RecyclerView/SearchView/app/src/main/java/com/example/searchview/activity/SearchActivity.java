package com.example.searchview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.searchview.databinding.ActivitySearchBinding;
import com.example.searchview.model.SearchModel;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;
    private SearchModel searchModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
    }

    private void viewBinding(){
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        searchModel = new SearchModel(this);
    }
}