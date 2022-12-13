package com.example.livedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.livedata.databinding.ActivityMainBinding;
import com.example.livedata.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        btnChange();
        observe();
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
            mainViewModel.changeImage();
            mainViewModel.changeName();
        });
    }

    private void observe(){
        Observer<String> nameObserver = name -> {
          binding.tvName.setText(name);
        };

        Observer<Uri> imageObserver = image -> {
            Glide.with(this).load(image).into(binding.ivImage);
        };

        mainViewModel.getName().observe(this, nameObserver);
        mainViewModel.getImage().observe(this, imageObserver);
    }

}