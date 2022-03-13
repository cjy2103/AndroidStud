package com.example.customrecyclerviewdetail.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.customrecyclerviewdetail.R;
import com.example.customrecyclerviewdetail.databinding.ActivityRecyclerItemDetailBinding;
import com.example.customrecyclerviewdetail.model.MyListItem;

import java.io.Serializable;
import java.util.ArrayList;

public class RecyclerItemDetailActivity extends AppCompatActivity {

    private ActivityRecyclerItemDetailBinding binding;

    private MyListItem myListItems;

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

        Bundle bundle = getIntent().getExtras();
        myListItems = (MyListItem) bundle.getSerializable("itemObject");
        imagePath   = myListItems.getList().get(0).getUri();
        title       = myListItems.getList().get(0).getTitle();
        describe    = myListItems.getList().get(0).getDescribe();
        youtubeLink = myListItems.getList().get(0).getChannelLink();

//        Intent intent = getIntent();
//        imagePath = intent.
//        imagePath   = intent.getStringExtra("imagePath");
//        title       = intent.getStringExtra("title");
//        describe    = intent.getStringExtra("describe");
//        youtubeLink = intent.getStringExtra("youtubeLink");
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