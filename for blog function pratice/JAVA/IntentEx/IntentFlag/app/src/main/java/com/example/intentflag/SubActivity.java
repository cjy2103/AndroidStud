package com.example.intentflag;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.intentflag.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        clickNormal();
        clickTest();
        clickIntentFlag();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
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

        binding.textView.setText(str);
    }

    private void clickNormal(){
        binding.btnNoOption.setOnClickListener(v->{
            Intent intent = new Intent(this,SubActivity.class);
            intent.putExtra("num",num);
            startActivity(intent);
        });
    }

    private void clickIntentFlag(){
        binding.btnSub.setOnClickListener(v->{
            Intent intent = new Intent(this,SubActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("num",num);
            startActivity(intent);
        });
    }

    private void clickTest(){
        binding.btnLaunch.setOnClickListener(v->{
            Intent intent = new Intent(this,TestActivity.class);
            intent.putExtra("num",0);
            startActivity(intent);
        });
    }
}