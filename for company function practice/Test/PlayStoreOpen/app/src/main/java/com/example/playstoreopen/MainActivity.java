package com.example.playstoreopen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.playstoreopen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        openPlayStore();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void openPlayStore(){
        binding.button.setOnClickListener(v->{
            Uri uri = Uri.parse("https://play.google.com");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });
    }
}