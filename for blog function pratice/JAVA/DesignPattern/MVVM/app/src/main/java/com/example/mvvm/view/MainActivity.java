package com.example.mvvm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.mvvm.model.Character;
import com.example.mvvm.vm.ImageViewModel;
import com.example.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        btnChange();
        observer();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);
    }

    private void btnChange(){
        binding.btnPick.setOnClickListener(v->{
            imageViewModel.nextCharacter();
        });
    }

    private void observer(){
        Observer<Character> imageObserver = character -> {
            String path  = "android.resource://" + this + "/";
            Uri    image = Uri.parse(path + character.getImage());
            Glide.with(this).load(image).into(binding.imageView);
            binding.tvName.setText(character.getName());
        };

        imageViewModel.getCharacterData().observe(this, imageObserver);
    }
}