package com.example.fragmentex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.fragmentex.databinding.ActivityMainBinding;
import com.example.fragmentex.fragment.BlueArchiveFragment;
import com.example.fragmentex.fragment.SpyFamilyFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BlueArchiveFragment blueArchiveFragment;
    private SpyFamilyFragment spyFamilyFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        clickBlueArchive();
        
        clickSpyFamiliy();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        blueArchiveFragment = new BlueArchiveFragment();
        spyFamilyFragment   = new SpyFamilyFragment();
        fragmentManager     = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, blueArchiveFragment).commit();
    }

    /**
     * @DESC: 프래그먼트 블루아카이브 이동
     */
    private void clickBlueArchive(){
        binding.btnFragmentBlueArchavie.setOnClickListener(v->{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame, blueArchiveFragment).commit();
        });
    }

    /**
     * @DESC: 프래그먼트 스파이패밀리 이동
     */
    private void clickSpyFamiliy(){
        binding.btnFragmentSpyFamiliy.setOnClickListener(v->{
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_frame, spyFamilyFragment).commit();
        });
    }
}