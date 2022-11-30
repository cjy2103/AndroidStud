package com.example.activityresultlauncer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activityresultlauncer.databinding.ActivitySubBinding;
import com.example.activityresultlauncer.model.SubModel;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    private SubModel subModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        clickSend();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        subModel = new SubModel(this, this);
    }

    private void clickSend(){
        binding.btnSend.setOnClickListener(v->{
            String word = binding.edtInput.getText().toString();
            subModel.sendData(word);
        });
    }
}