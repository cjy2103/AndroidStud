package com.example.intentflag;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.intentflag.databinding.ActivityTestBinding;

public class TestActivity extends AppCompatActivity {

    private ActivityTestBinding binding;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        clickTest();
    }

    private void viewBinding(){
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        Intent intent = getIntent();
        num = intent.getIntExtra("num", num);
        num ++;

        @SuppressLint("DefaultLocale")
        String str = String.format(
                """
                %d번째 SubActivity        
                """, num);

        binding.textView2.setText(str);
    }

    private void clickTest(){
        binding.btnLaunch.setOnClickListener(v->{
            Intent intent = new Intent(this,TestActivity.class);
            startActivity(intent);
        });
    }
}