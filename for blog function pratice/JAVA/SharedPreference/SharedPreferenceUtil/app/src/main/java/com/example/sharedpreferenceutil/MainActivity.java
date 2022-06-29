package com.example.sharedpreferenceutil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.sharedpreferenceutil.databinding.ActivityMainBinding;
import com.example.sharedpreferenceutil.util.SharedPreferenceUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        clickEvent();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickEvent(){
        clickSave();
        clickLoad();
    }

    private void clickSave(){
        binding.btnInput.setOnClickListener(v->{
            String str = binding.edtInput.getText().toString();

            SharedPreferenceUtil.putSharedPreference(this, "SaveString", str);
        });
    }

    private void clickLoad(){
        binding.btnLoad.setOnClickListener(v->{
            String str = SharedPreferenceUtil.getSharedPreference(this,"SaveString");
            binding.tvLoadData.setText(str);
        });
    }

}