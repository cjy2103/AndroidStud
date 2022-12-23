package com.example.objecttomap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.objecttomap.databinding.ActivityMainBinding;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        btnClick();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        person = new Person("철수",20,"남자");
    }

    @SuppressLint("SetTextI18n")
    private void btnClick(){
        binding.button.setOnClickListener(v->{
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> map = objectMapper.convertValue(person, Map.class);

            binding.tvValue.setText(map+"");
        });
    }
}