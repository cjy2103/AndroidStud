package com.example.sharedpreferenceex.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.sharedpreferenceex.R;
import com.example.sharedpreferenceex.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initBinding();

        initialize();

        clickDataSend();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        mActivity = SubActivity.this;
    }

    /**
     * @DESC: 데이터 저장 버튼 클릭
     */
    private void clickDataSend(){
        binding.btnDataSend.setOnClickListener(v->{
            SharedPreferences sharedPreferences = mActivity.getSharedPreferences("SaveData",MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Data","데이터 저장");
            editor.apply();

            finish();
        });
    }
}