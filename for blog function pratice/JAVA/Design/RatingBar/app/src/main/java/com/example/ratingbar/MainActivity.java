package com.example.ratingbar;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.RatingBar;

import com.example.ratingbar.databinding.ActivityMainBinding;
import com.example.ratingbar.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        ratingBarClick();

        btnLock();

        btnSolve();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @SuppressLint("SetTextI18n")
    private void ratingBarClick(){
        binding.ratingBar.setOnRatingBarChangeListener((ratingBar, value, b) -> {
            binding.tvSelect.setText("선택한 별의 개수: "+value);
        });
    }

    private void btnLock(){
        binding.btnLock.setOnClickListener(v->{
            binding.ratingBar.setIsIndicator(true);
        });
    }

    private void btnSolve(){
        binding.btnSolve.setOnClickListener(v->{
            binding.ratingBar.setIsIndicator(false);
        });
    }
}