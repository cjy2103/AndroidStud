package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.numberpicker.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        numberPickerRangeSetting();

        numberPickerSelect();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: NumberPicker 범위 설정
     */
    private void numberPickerRangeSetting(){
        binding.numberPicker.setMinValue(0);
        binding.numberPicker.setMaxValue(100);
    }

    /**
     * @DESC: NumberPicker 선택 이벤트
     */
    @SuppressLint("SetTextI18n")
    private void numberPickerSelect(){
        binding.tvNum.setText("선택된 숫자: "+binding.numberPicker.getValue());
        binding.numberPicker.setOnValueChangedListener((numberPicker, oldValue, newValue) -> {
            binding.tvNum.setText("선택된 숫자: "+newValue);
        });
    }
}