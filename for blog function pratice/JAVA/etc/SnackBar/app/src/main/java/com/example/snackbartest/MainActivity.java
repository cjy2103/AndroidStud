package com.example.snackbartest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.snackbartest.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        showSnackBar();
    }

    /**
     * @DESC: 뷰바인딩
     */
    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: SnackBar Test 버튼 클릭
     */
    private void showSnackBar(){
        binding.btnSanckbar.setOnClickListener(v->{
            Snackbar snackbar = Snackbar.make(binding.consParent,"SnackBarTest",Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction("OK", view -> {
                snackbar.dismiss();
            });

            snackbar.show();

        });
    }


}