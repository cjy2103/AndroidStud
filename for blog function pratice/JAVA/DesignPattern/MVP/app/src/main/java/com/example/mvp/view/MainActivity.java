package com.example.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.mvp.presenter.IPresenter;
import com.example.mvp.databinding.ActivityMainBinding;
import com.example.mvp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IView {

    private ActivityMainBinding binding;
    private IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        initialize();
        clickPickCard();

        Intent intent = new Intent();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        presenter = new MainPresenter(this);
    }


    private void clickPickCard(){
        binding.btnPick.setOnClickListener(v->{
            presenter.pickCard();
        });
    }


    @Override
    public void pickCard(Integer pickCard) {
        binding.imageView.setImageResource(pickCard);
    }
}