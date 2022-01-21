package com.example.customdialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.example.customdialog.databinding.ActivityMainBinding;
import com.example.customdialog.dialog.CustomDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        dialogShow();

    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 다이얼로그 열기
     */
    private void dialogShow(){
        binding.btnDialog.setOnClickListener(v->{
            FragmentManager fm = getSupportFragmentManager();

            DialogFragment dialogFragment = new CustomDialog();
            dialogFragment.show(fm,"customDialog");
        });
    }

}