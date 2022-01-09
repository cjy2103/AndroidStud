package com.example.datatransfer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.datatransfer.R;
import com.example.datatransfer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;
    private String [] strArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        openSubActivity();
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
        strArr = new String[]{"사과", "바나나", "귤"};
    }

    /**
     * @DESC: SubActivity로 데이터 전송
     */
    private void openSubActivity(){
        binding.btnOpen.setOnClickListener(v->{
            Intent intent = new Intent(mContext, SubActivity.class);
            intent.putExtra("String","문자열 전송");
            intent.putExtra("Integer",5);
            intent.putExtra("Boolean",true);
            intent.putExtra("StringArr", strArr);
            startActivity(intent);
        });
    }
}