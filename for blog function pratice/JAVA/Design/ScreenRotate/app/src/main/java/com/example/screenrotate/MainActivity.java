package com.example.screenrotate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.screenrotate.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private       ActivityMainBinding binding;
    private final ArrayList<String>   imageList = new ArrayList<>();
    private final ArrayList<String>   nameList  = new ArrayList<>();

    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        change();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        viewBinding();
        uiLoad();
        change();
    }

    private void viewBinding() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init() {
        String imageUri = "drawable://";
        imageList.add(imageUri + R.drawable.baknana);
        imageList.add(imageUri + R.drawable.mwama);
        imageList.add(imageUri + R.drawable.tamtam);

        nameList.add(getResources().getString(R.string.baknana));
        nameList.add(getResources().getString(R.string.mwamwa));
        nameList.add(getResources().getString(R.string.tamtam));
    }

    private void change() {
        binding.btnChange.setOnClickListener(v -> {
            num++;
            uiLoad();
        });
    }

    private void uiLoad() {
        int pos   = num % 3;
        Uri image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imageList.get(pos));
        Glide.with(this).load(image).into(binding.ivImage);
        binding.tvName.setText(nameList.get(pos));
    }


}