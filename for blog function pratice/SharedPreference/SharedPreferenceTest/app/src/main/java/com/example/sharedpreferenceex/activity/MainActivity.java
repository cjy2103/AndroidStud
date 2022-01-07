package com.example.sharedpreferenceex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.sharedpreferenceex.R;
import com.example.sharedpreferenceex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Context mContext;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();
        
        initialize();

        clickSubActivityMove();

        clickLoadData();
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
        mActivity = MainActivity.this;
    }

    /**
     * @DESC: SubActivity 이동
     */
    private void clickSubActivityMove(){
        binding.btnSubactivityMove.setOnClickListener(v->{
            Intent intent = new Intent(mContext,SubActivity.class);

            startActivity(intent);
        });
    }

    /**
     * @DESC: 저장된 데이터 호출
     */
    private void clickLoadData(){
        binding.btnLoad.setOnClickListener(v->{
            SharedPreferences sharedPreferences = mActivity.getSharedPreferences("SaveData",MODE_PRIVATE);

            String str = sharedPreferences.getString("Data","저장된 데이터 없음");

            binding.tvDataLoad.setText(str);

        });
    }
}