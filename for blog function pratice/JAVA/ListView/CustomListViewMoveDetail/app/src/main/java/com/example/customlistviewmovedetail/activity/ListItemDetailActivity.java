package com.example.customlistviewmovedetail.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.example.customlistviewmovedetail.R;
import com.example.customlistviewmovedetail.databinding.ActivityListItemDetailBinding;

public class ListItemDetailActivity extends AppCompatActivity {

    private ActivityListItemDetailBinding binding;
    private String title;
    private String describe;
    private String youtubeLink;
    private byte[] byteArray;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_detail);
        
        initBinding();

        loadData();

        settingInfo();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityListItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 전달받은 데이터 로드
     */
    private void loadData(){
        Intent intent = getIntent();
        byteArray   = getIntent().getByteArrayExtra("image");
        image       = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        title       = intent.getStringExtra("title");
        describe    = intent.getStringExtra("describe");
        youtubeLink = intent.getStringExtra("youtubeLink");
    }

    /**
     * @DESC: 초기 정보 세팅
     */
    private void settingInfo(){
        binding.imgCover.setImageBitmap(image);
        binding.tvTitle.setText(title);
        binding.tvDescribe.setText(describe);
        binding.tvYoutube.setText(youtubeLink);
    }
}