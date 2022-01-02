package com.example.youtubetest2.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.youtubetest2.R;
import com.example.youtubetest2.fragment.HomeFragment;
import com.example.youtubetest2.fragment.PlaylistFragment;
import com.example.youtubetest2.fragment.ProfileFragment;
import com.example.youtubetest2.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFragment           = new HomeFragment();
    private PlaylistFragment playlistFragment   = new PlaylistFragment();
    private SearchFragment searchFragment       = new SearchFragment();
    private ProfileFragment profileFragment     = new ProfileFragment();

    private BottomNavigationView menuBawah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(homeFragment);

        menuBawah = findViewById(R.id.menu_bawah);


        menuBawah.setSelectedItemId(R.id.menu_home);
        menuBawah.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {
                if (menuitem.isChecked()) {
                    return true;
                }
                else {
                    switch (menuitem.getItemId()){
                        case R.id.menu_home :
                            setFragment(homeFragment);
                            getSupportActionBar().setTitle("Home");
                            return true;
                        case R.id.menu_playlist :
                            setFragment(playlistFragment);
                            getSupportActionBar().setTitle("Playlist");
                            return true;
                        case R.id.menu_search :
                            setFragment(searchFragment);
                            getSupportActionBar().setTitle("Search");
                            return true;
                        case R.id.menu_profile :
                            setFragment(profileFragment);
                            getSupportActionBar().setTitle("Profile");
                            return true;
                        default:
                            setFragment(homeFragment);
                            getSupportActionBar().setTitle("Home");
                            return true;
                    }
                }
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_frame, fragment);
        getSupportActionBar().setTitle("Home");
        ft.commit();

    }


}