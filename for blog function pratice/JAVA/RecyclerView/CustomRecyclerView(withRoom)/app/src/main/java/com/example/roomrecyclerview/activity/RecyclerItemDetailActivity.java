package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.databinding.ActivityRecyclerItemDetailBinding;
import com.example.roomrecyclerview.model.MyListItem;
import com.example.roomrecyclerview.room.RoomDB;
import com.example.roomrecyclerview.util.LanguageCheck;
import com.example.roomrecyclerview.util.LogUtil;
import com.example.roomrecyclerview.util.SystemUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RecyclerItemDetailActivity extends AppCompatActivity {

    private ActivityRecyclerItemDetailBinding binding;

    private MyListItem myListItems;
    
    private String imagePath;
    private String title;
    private String describe;
    private String youtubeLink;
    private String imageCase;

    public static Context context;

    private SystemUtil systemUtil;
    private LanguageCheck languageCheck;

    private Typeface tfRoboto;
    private Typeface tfMaple;

    private RoomDB roomDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        
        initialize();

        dataLoad();

        uiSetting();

        updateClick();

        clickCancel();

        clickDelete();
    }

    @Override
    protected void onResume() {
        super.onResume();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this, binding.consItemDetail);
    }

    private void viewBinding(){
        binding = ActivityRecyclerItemDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    
    private void initialize(){
        systemUtil = new SystemUtil();

        languageCheck = new LanguageCheck();

        tfRoboto = getResources().getFont(R.font.font_english);
        tfMaple = getResources().getFont(R.font.font_korean);

        context = this;

        roomDB = RoomDB.getInstance(this);
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
        imageCase   = myListItems.getList().get(0).getImageCase();

    }

    /**
     * @DESC: UI 세팅
     */
    private void uiSetting(){
        Uri image;
        if(imageCase.equals("Local")) {
            image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imagePath);
        } else {
            image = Uri.parse(imagePath);
        }
        Glide.with(this).load(image).into(binding.ivImg);
        binding.tvTitle.setText(title);
        languageCheck.checkLanguage(binding.tvTitle, tfRoboto, tfMaple, 22, 24);;
        binding.tvDescribe.setText(describe);
        languageCheck.checkLanguage(binding.tvDescribe, tfRoboto, tfMaple, 18, 20);;
        if(youtubeLink.equals("")){
            binding.tvYoutube.setVisibility(View.GONE);
        } else {
            binding.tvYoutubeLink.setText(youtubeLink);
        }
    }

    private void updateClick(){
        binding.btnUpdate.setOnClickListener(v->{
            Intent intent = new Intent(this, UpdateActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("itemObject", myListItems);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    private void clickCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }

    public void refreshActivity(MyListItem item){
        Intent intent = new Intent(this, RecyclerItemDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("itemObject", item);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    @SuppressLint("CheckResult")
    private void clickDelete(){
        binding.consDelete.setOnClickListener(v->{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("삭제").
                    setMessage("해당 목록을 삭제하시겠습니까?").
                    setPositiveButton("OK", ((dialog, which) -> {
                        roomDB.dataDao().deleteItem(title).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(()->{
                                    Toast.makeText(this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    ((MainActivity)MainActivity.context).mainRecyclerRefresh();
                                    finish();
                                });
                    })).
                    setNegativeButton("Cancel", ((dialog, which) -> {

                    })).create().show();
        });
    }
}