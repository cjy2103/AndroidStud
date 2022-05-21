package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityMainBinding;
import com.example.roomrecyclerview.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @SuppressLint("StaticFieldLeak")
    public static Context context;
    private boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        clickInsert();

        context = this;

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(state){
            binding.tvTitle.setText("바뀔까?");
        } else {
            Toast.makeText(context, "state는 false 상태", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        state = false;
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickInsert(){
        binding.btnInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

    public void test(){
        state = true;
    }
}