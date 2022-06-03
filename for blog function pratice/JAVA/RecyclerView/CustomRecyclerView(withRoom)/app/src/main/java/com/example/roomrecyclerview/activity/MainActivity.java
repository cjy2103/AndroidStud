package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityMainBinding;
import com.example.roomrecyclerview.util.SystemUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SystemUtil systemUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();


        Typeface tfMapleLight = getResources().getFont(R.font.font_english);
        binding.tvTitle.setTypeface(tfMapleLight);

        clickInsert();

    }

    @Override
    protected void onResume() {
        super.onResume();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this, binding.consMain);
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        systemUtil = new SystemUtil();
    }

    private void clickInsert(){
        binding.consInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

}