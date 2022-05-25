package com.example.myapplication.activity;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.util.LogUtil;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private ActivityMainBinding binding;

    private boolean imageCallback = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickSelect();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(imageCallback){
            SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
            String path = sharedPreferences.getString("path","");
            if(!path.isEmpty()){
                Uri image = Uri.parse("android.resource://" + this.getPackageName() + "/" + path);
                Glide.with(this).load(image).into(binding.ivImage);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageCallback = false;
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        context = this;
    }

    private void clickSelect(){
        binding.btnSelect.setOnClickListener(v->{
            Intent intent = new Intent(this,AlbumListActivity.class);
            startActivity(intent);
        });
    }

    public void albumSelectCallback(){
        imageCallback = true;
    }

}