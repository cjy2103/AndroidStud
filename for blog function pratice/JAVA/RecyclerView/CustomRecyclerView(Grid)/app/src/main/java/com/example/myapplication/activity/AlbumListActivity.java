package com.example.myapplication.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.adapter.AlbumListAdapter;
import com.example.myapplication.databinding.ActivityAlbumListBinding;
import com.example.myapplication.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class AlbumListActivity extends AppCompatActivity {

    private ActivityAlbumListBinding binding;
    private ArrayList<String> imageList;
    private AlbumListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        listAdd();

        adapterConnection();

        gridItemClick();
    }

    private void viewBinding(){
        binding = ActivityAlbumListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        imageList = new ArrayList<>();
        binding.recyclerGird.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void listAdd(){
        String imageUri = "drawable://";
        imageList.add(imageUri + R.drawable.baknana);
        imageList.add(imageUri + R.drawable.djmax_clear_fail);
        imageList.add(imageUri + R.drawable.djmax_falling_in_love);
        imageList.add(imageUri + R.drawable.mwama);
        imageList.add(imageUri + R.drawable.tamtam);
    }

    private void adapterConnection(){
        adapter = new AlbumListAdapter(this,this, imageList);
        binding.recyclerGird.setAdapter(adapter);
    }

    private void gridItemClick(){
        adapter.setOnItemClickListener((v,pos) ->{
            SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("path",imageList.get(pos));
            editor.apply();

            finish();
        });
    }
}