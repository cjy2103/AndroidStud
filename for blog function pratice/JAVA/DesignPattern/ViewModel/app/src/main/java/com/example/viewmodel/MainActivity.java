package com.example.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.viewmodel.databinding.ActivityMainBinding;
import com.example.viewmodel.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        btnChange();
    }

    @Override
    protected void onResume() {
        super.onResume();
        uiChange();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mainViewModel     = new ViewModelProvider(this
                , (ViewModelProvider.Factory) new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);
    }

    private void btnChange(){
        binding.btnChange.setOnClickListener(v->{
            mainViewModel.num++;
            uiChange();
        });
    }

    private void uiChange(){
        Uri image = mainViewModel.changeImage();
        String title = mainViewModel.changeTitle();
        Glide.with(this).load(image).into(binding.ivImage);
        binding.tvName.setText(title);
    }
}