package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tablayout.databinding.ActivityMainBinding;
import com.example.tablayout.fragment.DjmaxFragment;
import com.example.tablayout.fragment.MidoriFragment;
import com.example.tablayout.fragment.MomoiFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DjmaxFragment djmaxFragment;
    private MomoiFragment momoiFragment;
    private MidoriFragment midoriFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        tabLayoutSelect();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void tabLayoutSelect(){
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeView(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeView(int pos){
        switch (pos){
            case 0 :

                break;
            case 1 :
                break;
            case 2 :
                break;
        }
    }

}