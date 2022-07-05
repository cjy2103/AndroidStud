package com.example.repeatviewrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.repeatviewrecycler.adapter.PlayLevelAdapter;
import com.example.repeatviewrecycler.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<String> playLevel;
    private PlayLevelAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        listAdd();

        recyclerConnection();

        itemClick();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        playLevel = new ArrayList<>();
        binding.playLevelRecycler.setLayoutManager(new LinearLayoutManager(this));
    }

    private void listAdd(){
        playLevel.add("Beginner");
        playLevel.add("Intermediate");
        playLevel.add("pro");
    }

    private void recyclerConnection(){
        adapter = new PlayLevelAdapter(this, playLevel);
        binding.playLevelRecycler.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void itemClick(){
        adapter.setOnItemClickListener((v, position) -> {
            adapter.notifyDataSetChanged();

        });
    }
}