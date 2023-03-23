package com.example.recyclerviewmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.recyclerviewmvvm.adapter.CharacterAdapter;
import com.example.recyclerviewmvvm.databinding.ActivityMainBinding;
import com.example.recyclerviewmvvm.vm.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel = new MainViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
//        CharacterAdapter adapter = new CharacterAdapter(viewModel.getCharacterList().getValue());
//        binding.recyclerList.setAdapter(adapter);
    }

    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
    }
}