package com.example.mvvmintent.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.mvvmintent.R;
import com.example.mvvmintent.databinding.ActivitySubBinding;
import com.example.mvvmintent.vm.SubViewModel;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;
    private SubViewModel viewModel = new SubViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding();
    }

    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sub);
        viewModel.init(this);
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);
    }
}