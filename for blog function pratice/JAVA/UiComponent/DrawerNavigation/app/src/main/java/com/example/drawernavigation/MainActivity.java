package com.example.drawernavigation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.drawernavigation.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        openDrawer();

        navigationItemClick();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        context = this;

        binding.navView.setItemIconTintList(null);
    }

    private void openDrawer(){
        binding.btnOpen.setOnClickListener(v->{
            binding.drawerLayout.openDrawer(GravityCompat.START);
        });
    }

    @SuppressLint("NonConstantResourceId")
    private void navigationItemClick(){
        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    Toast.makeText(context, "프로필 클릭", Toast.LENGTH_SHORT).show();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_naver:
                    Toast.makeText(context, "네이버 클릭", Toast.LENGTH_SHORT).show();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.nav_github:
                    Toast.makeText(context, "GitHub 클릭", Toast.LENGTH_SHORT).show();
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
            return false;
        });
    }
}