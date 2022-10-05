package com.example.kakaomap;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.ViewGroup;

import com.example.kakaomap.databinding.ActivityMainBinding;

import net.daum.mf.map.api.MapView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MapView mapView;
    ViewGroup mapViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mapView = new MapView(this);
        mapViewContainer = binding.mapView;
        mapViewContainer.addView(mapView);
    }



}