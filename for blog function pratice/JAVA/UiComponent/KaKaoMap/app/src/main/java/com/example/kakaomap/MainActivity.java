package com.example.kakaomap;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import com.example.kakaomap.util.HashKey;


public class MainActivity extends AppCompatActivity {

    MapView mapView = new MapView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        HashKey hashKey = new HashKey();
        hashKey.getSignatures(this);

    }



}