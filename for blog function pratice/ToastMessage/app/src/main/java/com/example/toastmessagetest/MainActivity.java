package com.example.toastmessagetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.toastmessagetest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();
        
        initialze();

        showToast();
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
    private void initialze(){
        mContext = MainActivity.this;
    }

    /**
     * @DESC: 토스트 메시지 출력
     */
    private void showToast(){
        binding.btnShowToast.setOnClickListener(v->{
            Toast.makeText(mContext,"토스트 메시지 출력",Toast.LENGTH_SHORT).show(); // 짧게 띄우기
//            Toast.makeText(mContext,"토스트 메시지 길게 출력",Toast.LENGTH_LONG).show(); // 길게 띄우기
        });
    }
}