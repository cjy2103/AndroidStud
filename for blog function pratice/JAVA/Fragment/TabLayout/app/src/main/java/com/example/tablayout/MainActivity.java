package com.example.tablayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.tablayout.databinding.ActivityMainBinding;
import com.example.tablayout.fragment.DjmaxFragment;
import com.example.tablayout.fragment.MidoriFragment;
import com.example.tablayout.fragment.MomoiFragment;
import com.example.tablayout.util.SystemUtil;
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

        init();

        tabLayoutSelect();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        djmaxFragment = new DjmaxFragment();
        momoiFragment = new MomoiFragment();
        midoriFragment = new MidoriFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, djmaxFragment).commit();

        SystemUtil.statusbarSetting(getWindow(),this,binding.consMain);
        SystemUtil.sofNavigationBarHide(getWindow());

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
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, djmaxFragment).commit();
                break;
            case 1 :
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, momoiFragment).commit();
                break;
            case 2 :
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, midoriFragment).commit();
                break;
        }
    }

}