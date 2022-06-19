package com.example.bottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bottomsheet.databinding.ActivityMainBinding;
import com.example.bottomsheet.fragment.BottomSheetFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        bottomSheetOpen();

    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void bottomSheetOpen(){
        binding.btnOpen.setOnClickListener(v->{
            BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
            bottomSheetFragment.show(getSupportFragmentManager(), "bottomSheet");
        });
    }

}


//        bottomSheetSetting();

//    private void bottomSheetSetting(){
//        BottomSheetBehavior.from(binding.bottomSheet).setPeekHeight(200);
//        BottomSheetBehavior.from(binding.bottomSheet).setState(BottomSheetBehavior.STATE_COLLAPSED);
//    }