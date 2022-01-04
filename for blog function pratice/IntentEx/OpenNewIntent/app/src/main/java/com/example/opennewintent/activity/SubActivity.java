package com.example.opennewintent.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import com.example.opennewintent.R;
import com.example.opennewintent.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initBinding();

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
     * @DESC: SubActivity 닫기
     */
    private void closeSubActivity(){
        binding.btnCloseSubActivity.setOnClickListener(v->{
            finish(); // 액티비티 종료
        });
    }

}