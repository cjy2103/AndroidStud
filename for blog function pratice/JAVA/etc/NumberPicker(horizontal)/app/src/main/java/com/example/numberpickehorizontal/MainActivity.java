package com.example.numberpickehorizontal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.numberpickehorizontal.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        horizontalNumberPicker();

        verticalNumberPicker();

    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 가로모드 NumberPicker
     */
    @SuppressLint("SetTextI18n")
    private void horizontalNumberPicker(){
        binding.tvNum.setText("선택된 숫자: "+binding.numberpickerHorizontal.getValue());
        binding.numberpickerHorizontal.setOnValueChangedListener((numberpicker,oldValue,newValue) ->{
            binding.tvNum.setText("선택된 숫자: "+binding.numberpickerHorizontal.getValue());
        });
    }

    /**
     * @DESC: 세로모드 NumberPicker
     */
    @SuppressLint("SetTextI18n")
    private void verticalNumberPicker(){
        binding.tvNum2.setText("선택된 숫자: "+binding.numberpickerVertical.getValue());
        binding.numberpickerVertical.setOnValueChangedListener((numberpicker,oldValue,newValue) ->{
            binding.tvNum2.setText("선택된 숫자: "+binding.numberpickerVertical.getValue());
        });
    }
}