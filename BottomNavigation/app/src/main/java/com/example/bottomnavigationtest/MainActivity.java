package com.example.bottomnavigationtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;


import com.example.bottomnavigationtest.Fragment.HomeFragment;
import com.example.bottomnavigationtest.Fragment.ImageFragment;
import com.example.bottomnavigationtest.Fragment.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private View decorView;
    private int    uiOption;
    private Fragment fHome, fImg, fInfo; // Fragment간 이동시 상태 저장을 위해

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);




        fHome = new HomeFragment();
        //첫 화면
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fHome).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            changeFragment(item);
            return true;
        });



        bottomSoftBarHide();


    }

    @SuppressLint("NonConstantResourceId")
    private void changeFragment(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.item_home_fragment:
                if(fHome==null){
                    fHome = new HomeFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, fHome).commit();
                }
                if(fHome != null) getSupportFragmentManager().beginTransaction().show(fHome).commit();
                if(fImg != null) getSupportFragmentManager().beginTransaction().hide(fImg).commit();
                if(fInfo != null) getSupportFragmentManager().beginTransaction().hide(fInfo).commit();
                break;
            case R.id.item_image_fragment:
                if(fImg==null){
                    fImg = new ImageFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, fImg).commit();
                }
                if(fHome != null) getSupportFragmentManager().beginTransaction().hide(fHome).commit();
                if(fImg != null) getSupportFragmentManager().beginTransaction().show(fImg).commit();
                if(fInfo != null) getSupportFragmentManager().beginTransaction().hide(fInfo).commit();
                break;
            case R.id.item_info_fragment:
                if(fInfo==null){
                    fInfo = new InfoFragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.main_frame, fInfo).commit();
                }
                if(fHome != null) getSupportFragmentManager().beginTransaction().hide(fHome).commit();
                if(fImg != null) getSupportFragmentManager().beginTransaction().hide(fImg).commit();
                if(fInfo != null) getSupportFragmentManager().beginTransaction().show(fInfo).commit();
                break;
            default:
                break;
        }
    }

    /**
     * @DESC: bottomSoftBar 숨기기
     */
    private void bottomSoftBarHide(){
        decorView = getWindow().getDecorView();
        uiOption = getWindow().getDecorView().getSystemUiVisibility();
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH )
            uiOption |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN )
//            uiOption |= View.SYSTEM_UI_FLAG_FULLSCREEN;
            if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT )
                uiOption |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;

        decorView.setSystemUiVisibility( uiOption );
    }


}