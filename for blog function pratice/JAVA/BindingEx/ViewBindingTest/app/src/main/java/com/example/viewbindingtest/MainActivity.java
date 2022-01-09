package com.example.viewbindingtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.viewbindingtest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        clickWordChange();
    }

    /**
     * @DESC: 뷰 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 단어변경 버튼 클릭
     */
    private void clickWordChange(){
        binding.btnWordChange.setOnClickListener(v->{
            binding.tvWord.setText("단어변경");
        });
    }
}