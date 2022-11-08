package com.example.textflowerror;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;

import com.example.textflowerror.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        textFlow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void textFlow(){
        binding.tvFlow.setSingleLine(true);
        binding.tvFlow.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        binding.tvFlow.postDelayed(()-> binding.tvFlow.setSelected(true),1500);
    }
}