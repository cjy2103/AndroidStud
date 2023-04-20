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
    private NavController navController;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();

        init();

        iconChange();
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
        navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNav, navController);

        menu = binding.bottomNav.getMenu();

        SystemUtil.sofNavigationBarHide(getWindow());
        SystemUtil.statusbarSetting(getWindow(),this,binding.mainConstraint);
    }

    private void iconChange(){
        navController.addOnDestinationChangedListener(((controller, destination, bundle) -> {
            viewModel.updateBottomNavIcon(menu, destination);
        }));
    }

}