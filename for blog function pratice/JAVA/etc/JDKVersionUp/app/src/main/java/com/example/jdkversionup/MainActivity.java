package com.example.jdkversionup;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;

import com.example.jdkversionup.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        changeColor();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void changeColor(){
        binding.btnColor.setOnClickListener(v->{
            try {
                String str = binding.edtNumber.getText().toString();
                if (TextUtils.isEmpty(str)) {
                    int color = 0;
                    colorCase(color);
                    binding.edtNumber.setText("");
                } else {
                    int color = Integer.parseInt(str);
                    colorCase(color);
                    binding.edtNumber.setText("");
                }
            } catch (NumberFormatException e){
                int color = 0;
                colorCase(color);
            }

        });
    }

    private void colorCase(int color){
        switch (color){
            case 1 -> binding.consBackground.setBackgroundColor(Color.parseColor("#01DFD7"));
            case 2 -> binding.consBackground.setBackgroundColor(Color.parseColor("#8181F7"));
            case 3,4,5 -> binding.consBackground.setBackgroundColor(Color.parseColor("#D8F781"));
            default -> binding.consBackground.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }
}