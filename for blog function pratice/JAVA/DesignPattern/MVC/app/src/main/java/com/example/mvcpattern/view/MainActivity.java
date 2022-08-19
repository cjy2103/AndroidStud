package com.example.mvcpattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvcpattern.controller.MainController;
import com.example.mvcpattern.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;
    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        cardPick();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainController = new MainController(this);
    }

    private void cardPick(){
        binding.btnPick.setOnClickListener(v->{
            mainController.pickCard();
        });
    }
}