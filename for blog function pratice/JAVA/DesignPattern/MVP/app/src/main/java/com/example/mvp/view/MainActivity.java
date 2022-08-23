package com.example.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvp.IPresenter;
import com.example.mvp.IView;
import com.example.mvp.databinding.ActivityMainBinding;
import com.example.mvp.model.MainRepository;
import com.example.mvp.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements IView {

    private ActivityMainBinding binding;

    IPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        initialize();

        clickPickCard();
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