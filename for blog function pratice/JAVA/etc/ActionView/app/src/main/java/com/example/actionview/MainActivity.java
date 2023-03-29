package com.example.actionview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.actionview.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        binding.toolbar.setTitle("ActionBar 테스트");
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Gradle 8.0 부터 리소스 ID가 Final 이 아니라 switch case문 권장되지 않음
        if(item.getItemId() == R.id.action_search){
            Toast.makeText(this, "검색창 클릭", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_settings){
            Toast.makeText(this, "Setting 클릭", Toast.LENGTH_SHORT).show();
        } else if(item.getItemId() == R.id.action_settings2){
            Toast.makeText(this, "Setting2 클릭", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}