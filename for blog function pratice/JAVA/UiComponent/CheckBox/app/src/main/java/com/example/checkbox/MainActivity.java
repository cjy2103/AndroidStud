package com.example.checkbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.checkbox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        openCheckBox();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void openCheckBox(){
        binding.btnOpen.setOnClickListener(v->{
            FragmentManager fm = getSupportFragmentManager();
            DialogFragment dialogFragment = new CheckBoxDialog();
            dialogFragment.show(fm, "CheckBoxDialog");

        });

    }
}