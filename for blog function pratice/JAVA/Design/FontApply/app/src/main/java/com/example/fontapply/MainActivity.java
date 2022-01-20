package com.example.fontapply;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.fontapply.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        fontSetting();

        setText();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        mContext = MainActivity.this;
    }

    /**
     * @DESC: 폰트세팅
     */
    private void fontSetting(){
        Typeface tfMapleBold    = Typeface.createFromAsset(mContext.getAssets(), "Maplestory Bold.ttf");
        Typeface tfMapleLight   = Typeface.createFromAsset(mContext.getAssets(), "Maplestory Light.ttf");

        binding.tvMapleBold.setTypeface(tfMapleBold);
        binding.tvMapleLight.setTypeface(tfMapleLight);
    }

    /**
     * @DESC: 텍스트 세팅
     */
    @SuppressLint("SetTextI18n")
    private void setText(){
        binding.tvMapleBold.setText("메이플 스토리 Bold체");
        binding.tvMapleLight.setText("메이플 스토리 Light체");
    }
    
}