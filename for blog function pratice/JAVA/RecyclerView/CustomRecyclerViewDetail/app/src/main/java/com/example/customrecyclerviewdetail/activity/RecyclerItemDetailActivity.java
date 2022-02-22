package com.example.customrecyclerviewdetail.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.customrecyclerviewdetail.R;
import com.example.customrecyclerviewdetail.databinding.ActivityRecyclerItemDetailBinding;

public class RecyclerItemDetailActivity extends AppCompatActivity {

    private ActivityRecyclerItemDetailBinding binding;

    private String imagePath, title, describe, youtubeLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_item_detail);

        initBinding();

        dataLoad();

        uiSetting();
        
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityRecyclerItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 데이터 로드
     */
    private void dataLoad(){
        Intent intent = getIntent();
        imagePath   = intent.getStringExtra("imagePath");
        title       = intent.getStringExtra("title");
        describe    = intent.getStringExtra("describe");
        youtubeLink = intent.getStringExtra("youtubeLink");
    }

    /**
     * @DESC: UI 세팅
     */
    private void uiSetting(){
        Uri image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imagePath);
        Glide.with(this).load(image).into(binding.ivImg);
        binding.tvTitle.setText(title);
        binding.tvDescribe.setText(describe);
        binding.tvYoutubeLink.setText(youtubeLink);
    }
}