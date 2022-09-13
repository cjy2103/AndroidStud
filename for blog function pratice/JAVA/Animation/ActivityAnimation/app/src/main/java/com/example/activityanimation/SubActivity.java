package com.example.activityanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.activityanimation.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        clickBack();
    }

    private void viewBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickBack(){
        binding.button.setOnClickListener(v->{
            finish();
            overridePendingTransition(R.anim.none, R.anim.right_left_out);
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.none, R.anim.right_left_out);
    }
}