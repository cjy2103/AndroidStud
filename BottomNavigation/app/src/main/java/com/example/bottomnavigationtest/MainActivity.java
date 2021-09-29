package com.example.bottomnavigationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigationtest.Fragment.HomeFragment;
import com.example.bottomnavigationtest.Fragment.ImageFragment;
import com.example.bottomnavigationtest.Fragment.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        
        //첫 화면
        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,new HomeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            changeFragment(item);
            return true;
        });

    }

    @SuppressLint("NonConstantResourceId")
    private void changeFragment(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.item_home_fragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HomeFragment()).commit();
                break;
            case R.id.item_image_fragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new ImageFragment()).commit();
                break;
            case R.id.item_info_fragment:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new InfoFragment()).commit();
                break;
            default:
                break;
        }
    }

}