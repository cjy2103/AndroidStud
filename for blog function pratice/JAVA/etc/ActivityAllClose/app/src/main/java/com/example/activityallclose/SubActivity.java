package com.example.activityallclose;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.activityallclose.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        openActivity();
        closeActivity();
        closeAllActivity();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    private void initialize(){
        Intent intent = getIntent();
        number = intent.getIntExtra("number",1);
        binding.tvCurrent.setText("현재"+number+"번째 창");
    }

    private void openActivity(){
        binding.btnOpen.setOnClickListener(v->{
            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("number",number+1);
            startActivity(intent);
        });
    }

    private void closeActivity(){
        binding.btnClose.setOnClickListener(v->{
            finish();;
        });
    }

    private void closeAllActivity(){
        binding.btnAllClose.setOnClickListener(v->{
            finishAffinity();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}