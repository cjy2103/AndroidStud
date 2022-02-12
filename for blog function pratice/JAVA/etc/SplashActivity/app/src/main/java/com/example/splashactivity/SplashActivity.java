package com.example.splashactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.bumptech.glide.Glide;
import com.example.splashactivity.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initBinding();
        
        imageLoading();

        moveMain();

    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 이미지 로딩
     */
    private void imageLoading(){
        Glide.with(this).load(R.drawable.overme).into(binding.ivSplash);
    }

    /**
     * @DESC: Main 페이지 이동
     */
    private void moveMain(){
        runOnUiThread(()->{
            new Handler(Looper.myLooper()).postDelayed(() -> {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            },2000);
        });
    }
}