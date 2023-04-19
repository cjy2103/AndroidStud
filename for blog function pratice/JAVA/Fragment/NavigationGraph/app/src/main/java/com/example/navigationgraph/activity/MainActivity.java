package com.example.navigationgraph.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.navigationgraph.R;
import com.example.navigationgraph.databinding.ActivityMainBinding;
import com.example.navigationgraph.util.SystemUtil;
import com.example.navigationgraph.vm.MainViewModel;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel = new MainViewModel();
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();

        init();

//        clickBottomTab();
//
//        observeViewModel();
    }

    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }

    private void init(){
        binding.bottomNav.setItemIconTintList(null);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.main_frame);
        assert navHostFragment != null;
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNav, navController);

//        menu = binding.bottomNav.getMenu();
//        menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax_fail);

        SystemUtil.sofNavigationBarHide(getWindow());
        SystemUtil.statusbarSetting(getWindow(),this,binding.mainConstraint);
    }


//    @SuppressLint("NonConstantResourceId")
//    private void clickBottomTab(){
//        binding.bottomNav.setOnItemSelectedListener(item -> {
//            menu.findItem(R.id.djmax_fragment).setIcon(R.drawable.iv_djmax);
//            menu.findItem(R.id.momoi_fragment).setIcon(R.drawable.iv_momoi);
//            menu.findItem(R.id.midori_fragment).setIcon(R.drawable.iv_midori);
//            switch (item.getItemId()){
//                case R.id.djmax_fragment ->  viewModel.setItemIconRes(R.id.djmax_fragment, R.drawable.iv_djmax_fail);
//                case R.id.momoi_fragment ->  viewModel.setItemIconRes(R.id.momoi_fragment, R.drawable.iv_alice);
//                case R.id.midori_fragment ->  viewModel.setItemIconRes(R.id.midori_fragment, R.drawable.iv_yuse);
//            }
//            return false;
//        });
//    }
//
//    private void observeViewModel(){
//        viewModel.getItemIconRes().observe(this, itemIconRes -> {
//            MenuItem item = binding.bottomNav.getMenu().findItem(itemIconRes.getItemId());
//            if (item != null) {
//                item.setIcon(itemIconRes.getIconResId());
//            }
//        });
//    }

}