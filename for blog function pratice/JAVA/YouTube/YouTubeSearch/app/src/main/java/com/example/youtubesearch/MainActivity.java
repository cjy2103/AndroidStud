package com.example.youtubesearch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;

import com.example.youtubesearch.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        edtSearch();
    }

    private void viewBinding(){
        binding  = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void edtSearch(){
        binding.edtSearch.setOnEditorActionListener((v,actionId, event) ->{

            if(EditorInfo.IME_ACTION_SEARCH == actionId){
                // TODO

            }

            return true;
        });
    }
}

