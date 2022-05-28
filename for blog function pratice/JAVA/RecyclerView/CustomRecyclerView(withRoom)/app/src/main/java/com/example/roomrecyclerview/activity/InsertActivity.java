package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.dialog.ImageSelectDialog;
import com.example.roomrecyclerview.databinding.ActivityInsertBinding;
import com.example.roomrecyclerview.util.LogUtil;

public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;

    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        clickInsert();

        context = this;

    }

    private void viewBinding(){
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickInsert(){
        binding.btnInsert.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();

            DialogFragment dialogFragment = new ImageSelectDialog();
            Bundle bundle = new Bundle();
            bundle.putString("Test","테스트");
            dialogFragment.setArguments(bundle);
            dialogFragment.show(fragmentManager,"Dialog");
        });
    }

    public void test(){
        LogUtil.log("테스트");
    }
}