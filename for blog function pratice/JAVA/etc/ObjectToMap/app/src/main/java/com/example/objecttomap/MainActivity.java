package com.example.objecttomap;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.objecttomap.databinding.ActivityMainBinding;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
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

            ArrayList<Person> persons = new ArrayList<>();
            persons.add(new Person("철수",20,"남자"));
            persons.add(new Person("철수",20,"남자"));
            persons.add(new Person("철수",20,"남자"));

            ArrayList<String> result = objectMapper.convertValue(persons, ArrayList.class);

            Log.v("테스트:",""+result);
//            Map<String, Object> map = objectMapper.convertValue(person, Map.class);

//            binding.tvValue.setText(result+"");
        });
    }
}