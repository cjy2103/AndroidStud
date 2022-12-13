package com.example.mvcpattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvcpattern.databinding.ActivityMainBinding;
import com.example.mvcpattern.model.ImageModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageModel imageModel;
//    private MainController mainController;

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
        imageModel = new ImageModel(this);
//        mainController = new MainController(this);
    }

    private void cardPick(){
        binding.btnPick.setOnClickListener(v->{
//            mainController.pickCard();
            imageModel.pickCard();
        });
    }

    public void imageUpdate(int select){
        binding.imageView.setImageResource(select);
    }
}