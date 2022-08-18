package com.example.checkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
            String packageName = "com.kakaogames.umamusume";
            Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            if(intent != null){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName)));
                } catch(android.content.ActivityNotFoundException e){
                    Toast.makeText(this, "플레이스토어 앱 존재하지 않음", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void btnPokemonCheck(){

        binding.btnPokemonCheck.setOnClickListener(v->{
            String packageName = "com.nianticlabs.pokemongo";
            Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
            if(intent != null){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } else {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+packageName)));
                } catch(android.content.ActivityNotFoundException e){
                    Toast.makeText(this, "플레이스토어 앱 존재하지 않음", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}