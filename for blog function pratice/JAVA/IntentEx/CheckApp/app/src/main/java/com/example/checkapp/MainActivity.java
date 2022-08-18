package com.example.checkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.checkapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        btnUmaCheck();

        btnPokemonCheck();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void btnUmaCheck(){
        binding.btnUmaCheck.setOnClickListener(v->{
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.kakaogames.umamusume");
            if(intent != null){
                Toast.makeText(this, "우마무스메 있음", Toast.LENGTH_SHORT).show();
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(this, "우마무스메 없음", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void btnPokemonCheck(){
        binding.btnPokemonCheck.setOnClickListener(v->{
            Intent intent = getPackageManager().getLaunchIntentForPackage("com.nianticlabs.pokemongo");
            if(intent != null){
                Toast.makeText(this, "포켓몬고 있음", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "포켓몬고 없음", Toast.LENGTH_SHORT).show();
            }
        });
    }
}