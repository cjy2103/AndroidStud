package com.example.imageopacitiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.imageopacitiy.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        srcOpacity();

        backgroundOpacity();

        moveSub();

    }

    /**
     * @DESC: 뷰바인딩
     */
    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: src/srcCompat 이미지 Opacity
     */
    private void srcOpacity(){
        Drawable imageOpacity = binding.ivKaruOpacity.getDrawable();
        imageOpacity.setAlpha(50);
    }

    /**
     * @DESC: background 이미지 Opacity
     */
    private void backgroundOpacity(){
        Drawable imageOpacity = binding.ivBlueArchiveOpacity.getBackground();
        imageOpacity.setAlpha(50);
    }

    /**
     * @DESC: SubActivity 이동
     */
    private void moveSub(){
        binding.button.setOnClickListener(v->{
            Intent intent = new Intent(this, SubActivity.class);
            startActivity(intent);
        });
    }
}