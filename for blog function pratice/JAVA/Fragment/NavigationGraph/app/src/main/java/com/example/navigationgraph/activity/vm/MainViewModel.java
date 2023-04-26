package com.example.navigationgraph.activity.vm;

import android.annotation.SuppressLint;
import android.view.Menu;

import androidx.navigation.NavDestination;

import com.example.navigationgraph.R;

public class MainViewModel {

    @SuppressLint("NonConstantResourceId")
    public void updateBottomNavIcon(Menu menu, NavDestination destination){
        menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax);
        menu.findItem(R.id.momoi_fragment).setIcon(R.drawable.iv_momoi);
        menu.findItem(R.id.midori_fragment).setIcon(R.drawable.iv_midori);
        switch (destination.getId()){
            case R.id.djmax_fragment -> menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax_fail);
            case R.id.momoi_fragment ->  menu.findItem(R.id.momoi_fragment).setIcon(R.drawable.iv_alice);
            case R.id.midori_fragment -> menu.findItem(R.id.midori_fragment).setIcon(R.drawable.iv_yuse);
        }
    }
}
