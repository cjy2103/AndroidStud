package com.example.floatingbutton;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.floatingbutton.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private Animation fabOpen, fabClose;

    private boolean isFapOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickFab();

        clickFabJazz();

        clickFabDiyap();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
    }

    private void clickFab(){
        binding.fab.setOnClickListener(v->{
            animation();
        });
    }

    private void clickFabJazz(){
        binding.fabJazz.setOnClickListener(v->{
            animation();
            binding.imageView.setImageResource(R.drawable.uma_jazz);
        });
    }

    private void clickFabDiyap(){

        binding.fabDiyap.setOnClickListener(v->{
            animation();
            binding.imageView.setImageResource(R.drawable.uma_diyap);
        });
    }

    private void animation(){
        if(isFapOpen){
            ObjectAnimator.ofFloat(binding.fab, View.ROTATION,45f,0f).start();
            binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#ACF6FF")));
            binding.fabJazz.startAnimation(fabClose);
            binding.fabDiyap.startAnimation(fabClose);
            isFapOpen = false;
        } else {
            ObjectAnimator.ofFloat(binding.fab, View.ROTATION,0f,45f).start();
            binding.fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#41A9FB")));
            binding.fabJazz.startAnimation(fabOpen);
            binding.fabDiyap.startAnimation(fabOpen);
            isFapOpen = true;
        }
    }


}