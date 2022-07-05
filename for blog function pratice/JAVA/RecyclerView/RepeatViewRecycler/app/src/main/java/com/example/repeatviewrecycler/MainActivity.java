package com.example.repeatviewrecycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.repeatviewrecycler.adapter.PlayLevelAdapter;
import com.example.repeatviewrecycler.databinding.ActivityMainBinding;
import com.example.repeatviewrecycler.model.PlayLevel;
import com.example.repeatviewrecycler.model.PlayLevelModel;
import com.example.repeatviewrecycler.util.LogUtil;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<PlayLevel> playLevel;
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
        addItem("Beginner");
        addItem("Intermediate");
        addItem("Pro");
    }

    private void addItem(String level){
        PlayLevelModel playLevelModel = new PlayLevelModel();
        playLevelModel.setLevel(level);
        playLevelModel.setChecked(false);

        PlayLevel playItem = new PlayLevel();

        ArrayList<PlayLevelModel> items = new ArrayList<>();

        items.add(playLevelModel);

        playItem.setList(items);

        playLevel.add(playItem);

    }

    private void recyclerConnection(){
        adapter = new PlayLevelAdapter(this, playLevel);
        binding.playLevelRecycler.setAdapter(adapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void itemClick(){
        adapter.setOnItemClickListener((v, position) -> {
            selectClear();
            playLevel.get(position).getList().get(0).setChecked(true);
            adapter.notifyDataSetChanged();
        });
    }

    private void selectClear(){
        for(int i=0;i<playLevel.size();i++){
            playLevel.get(i).getList().get(0).setChecked(false);
        }
    }
}