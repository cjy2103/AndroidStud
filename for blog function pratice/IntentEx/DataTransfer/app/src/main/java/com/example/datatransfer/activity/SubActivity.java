package com.example.datatransfer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.datatransfer.R;
import com.example.datatransfer.databinding.ActivitySubBinding;

import java.util.Arrays;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private String receiveString;
    private int receiveInt;
    private boolean receiveBoolean;
    private String [] receiveStringArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initBinding();

        initialize();

        settingTextView();

        closeSubActivity();


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
        Intent intent = getIntent();
        receiveString       = intent.getStringExtra("String");
        receiveInt          = intent.getIntExtra("Integer",0);
        receiveBoolean      = intent.getBooleanExtra("Boolean",false);
        receiveStringArr    = intent.getStringArrayExtra("StringArr");
    }

    /**
     * @DESC: 텍스트뷰 값 넣기.
     */
    @SuppressLint("SetTextI18n")
    private void settingTextView(){
        binding.tvString.setText(receiveString);
        binding.tvInt.setText(receiveInt+"");
        binding.tvBoolean.setText(receiveBoolean+"");
        binding.tvStringArr.setText(Arrays.toString(receiveStringArr));
    }

    /**
     * @DESC: SubActivity 닫기
     */
    private void closeSubActivity(){
        binding.btnClose.setOnClickListener(v->{
            finish();
        });
    }
}