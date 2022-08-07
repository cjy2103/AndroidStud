package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        binding = null;

        binding.button.setOnClickListener(v->{
            Log.v("테스트","테스트");
        });
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        FirebaseCrashlytics.getInstance();
    }
}