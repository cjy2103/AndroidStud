package com.example.activityanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.activityanimation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        binding.btnOpen.setOnClickListener(v->{
            Intent intent = new Intent(this,SubActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.left_right_enter, R.anim.none);
        });
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}