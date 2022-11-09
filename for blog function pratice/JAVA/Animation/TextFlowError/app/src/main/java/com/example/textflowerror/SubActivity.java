package com.example.textflowerror;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.example.textflowerror.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        textFlow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
        handler.removeCallbacksAndMessages(null);
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        handler = new Handler();
    }

    private void textFlow(){
        binding.tvFlow.setSingleLine(true);
        binding.tvFlow.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        Runnable runnable = () -> binding.tvFlow.post(()-> binding.tvFlow.setSelected(true));

        handler.postDelayed(runnable,1500);

    }
}