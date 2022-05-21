package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.roomrecyclerview.R;

public class InsertActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

    }

    @Override
    protected void onPause() {
        super.onPause();
        ((MainActivity)MainActivity.context).test();
    }
}