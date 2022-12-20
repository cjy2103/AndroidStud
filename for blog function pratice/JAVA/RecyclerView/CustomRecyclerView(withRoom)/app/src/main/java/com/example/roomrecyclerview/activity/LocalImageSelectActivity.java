package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.adapter.LocalAlbumListAdapter;
import com.example.roomrecyclerview.activity.dialog.ImageSelectDialog;
import com.example.roomrecyclerview.databinding.ActivityLocalImageSelectBinding;
import com.example.roomrecyclerview.util.ImageUtil;
import com.example.roomrecyclerview.util.SystemUtil;

import java.util.ArrayList;

public class LocalImageSelectActivity extends AppCompatActivity {

    private ActivityLocalImageSelectBinding binding;
    private ArrayList<String> imageList;
    private LocalAlbumListAdapter adapter;
    private SystemUtil systemUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        listAdd();

        adapterConnection();

        gridItemClick();
    }

    @Override
    protected void onResume() {
        super.onResume();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this, binding.consLocal);
    }

    private void viewBinding(){
        binding = ActivityLocalImageSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        imageList = new ArrayList<>();

        systemUtil = new SystemUtil();

        Typeface tfMapleLigth = getResources().getFont(R.font.maplestory_light);
        binding.tvTitle.setTypeface(tfMapleLigth);

        imageList = new ArrayList<>();
        binding.recyclerImage.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void listAdd(){
        String imageUri = "drawable://";
        imageList.add(imageUri + R.drawable.baknana);
        imageList.add(imageUri + R.drawable.djmax_clear_fail);
        imageList.add(imageUri + R.drawable.djmax_falling_in_love);
        imageList.add(imageUri + R.drawable.mwama);
        imageList.add(imageUri + R.drawable.tamtam);
    }

    private void adapterConnection(){
        adapter = new LocalAlbumListAdapter(this, this, imageList);
        binding.recyclerImage.setAdapter(adapter);
    }

    private void gridItemClick(){
        adapter.setOnItemClickListener((v, position) ->{
            SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("path",imageList.get(position));
            editor.putBoolean("localSelect",true);
            editor.apply();
            finish();

        });
    }

}