package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        btnAppSubscribe();
        btnAllSubscribe();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void btnAppSubscribe(){
        binding.btnAppSubscribe.setOnClickListener(v->{
            Uri uri = Uri.parse("https://play.google.com/store/account/subscriptions?sku=your-sub-product-id&package=your-app-package");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });
    }

    private void btnAllSubscribe(){
        binding.btnAllSubscribe.setOnClickListener(v->{
            Uri uri = Uri.parse("https://play.google.com/store/account/subscriptions");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.android.vending");
            startActivity(intent);
        });
    }
}