package com.example.nodesignpattern;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.nodesignpattern.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<Integer> arrayList;

    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        binding.btnPick.setOnClickListener(v->{
            int select = random.nextInt(arrayList.size());
            binding.imageView.setImageResource(arrayList.get(select));
        });
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        random = new Random();
        arrayList = new ArrayList<>(Arrays.asList(R.drawable.baknana,R.drawable.djmax_clear_fail,R.drawable.djmax_falling_in_love
                ,R.drawable.mwama, R.drawable.tamtam));
    }
}