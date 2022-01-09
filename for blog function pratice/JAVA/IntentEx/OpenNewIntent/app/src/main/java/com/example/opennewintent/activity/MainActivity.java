package com.example.opennewintent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.opennewintent.R;
import com.example.opennewintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();
        
        clickMoveSubActivity();
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
        mContext  = MainActivity.this;
    }

    /**
     * @DESC: 서브 액티비티 이동
     */
    private void clickMoveSubActivity(){
        binding.btnOpenSubActivity.setOnClickListener(v->{
            Intent intent = new Intent(mContext,SubActivity.class);
            startActivity(intent);
        });
    }
}