package com.example.activityresultlauncer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activityresultlauncer.databinding.ActivityMainBinding;
import com.example.activityresultlauncer.model.MainModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainModel mainModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        moveSub();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainModel = new MainModel(this, this);
    }

    private void moveSub(){
        binding.btnMoveSub.setOnClickListener(v->{
            mainModel.moveSubActivity();
        });
    }

    public void callbackWord(String word){
        binding.tvWord.setText(word);
    }
}