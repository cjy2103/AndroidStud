package com.example.mvvm.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.mvvm.activity.vm.ImageViewModel;
import com.example.mvvm.databinding.ActivitySubBinding;
import com.example.mvvm.util.ImageUtil;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    private ImageViewModel imageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewbinding();
        init();
        btnChange();
        observer();
    }

    private void viewbinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        imageViewModel = new ViewModelProvider(this).get(ImageViewModel.class);

    }

    private void btnChange(){
        binding.btnPick.setOnClickListener(v->{
            imageViewModel.imagePick();
        });
    }

    private void observer(){
        Observer<String> imageObserver = image -> {
            for(int i=0;i< ImageUtil.imageTable.length;i++){
                
            }
        };
    }
}