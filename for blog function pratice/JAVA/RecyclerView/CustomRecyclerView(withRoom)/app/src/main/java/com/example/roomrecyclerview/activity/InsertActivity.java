package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.dialog.ImageSelectDialog;
import com.example.roomrecyclerview.databinding.ActivityInsertBinding;
import com.example.roomrecyclerview.util.LogUtil;

public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;

    public static Context context;
    private boolean imageCallback = false;
    private boolean localCallback = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickInsert();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        imageCallback = false;
    }

    private void viewBinding(){
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        context = this;
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

    public void albumSelectCallback(boolean call){
        SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
        String path = sharedPreferences.getString("path","");
        if(!path.isEmpty()){
            Uri image = Uri.parse("android.resource://" + this.getPackageName() + "/" + path);
            Glide.with(this).load(image).into(binding.imageView);
        }
    }
}