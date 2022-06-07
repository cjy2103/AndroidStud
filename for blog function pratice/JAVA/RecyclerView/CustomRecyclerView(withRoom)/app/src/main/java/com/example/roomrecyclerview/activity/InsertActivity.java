package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.dialog.ImageSelectDialog;
import com.example.roomrecyclerview.databinding.ActivityInsertBinding;
import com.example.roomrecyclerview.room.Data;
import com.example.roomrecyclerview.room.RoomDB;
import com.example.roomrecyclerview.util.LogUtil;
import com.example.roomrecyclerview.util.SystemUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;

    public static Context context;

    private SystemUtil systemUtil;

    private RoomDB roomDB;
    private Data data;

    private String imageCase = "";

    private String imagePath;
    private String str = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickImageInsert();

        clickCancel();

        clickSave();
    }

    @Override
    protected void onResume() {
        super.onResume();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this, binding.consInsert);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void viewBinding(){
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        context = this;
        systemUtil = new SystemUtil();

        roomDB = RoomDB.getInstance(this);
        data = new Data();
    }

    private void clickImageInsert(){
        binding.btnInsert.setOnClickListener(v->{
            FragmentManager fragmentManager = getSupportFragmentManager();

            DialogFragment dialogFragment = new ImageSelectDialog();
            dialogFragment.show(fragmentManager,"Dialog");
        });
    }

    public void albumSelectCallback(boolean localImage){
        SharedPreferences sharedPreferences = this.getSharedPreferences("image",MODE_PRIVATE);
        imagePath = sharedPreferences.getString("path","");
        if(!imagePath.isEmpty()){
            Uri image;
            if(localImage){
                image = Uri.parse("android.resource://" + this.getPackageName() + "/" + imagePath);
                imageCase = "Local";
            } else {
                image = Uri.parse(imagePath);
                imageCase = "Gallery";
            }
            Glide.with(this).load(image).into(binding.ivImage);
        }
    }

    private void clickCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }

    private void clickSave(){
        binding.btnSave.setOnClickListener(v->{
            String name = binding.edtTitle.getText().toString();
            String describe = binding.edtDescribe.getText().toString();
            if(name.length() == 0 || describe.length() == 0){
                Toast.makeText(this, "이름과 설명은 필수입력 사항입니다.", Toast.LENGTH_SHORT).show();
            } else {
                dbInsert();
            }
        });
    }

    @SuppressLint("CheckResult")
    private void dbInsert(){
        data.setTitle(binding.edtTitle.getText().toString());
        data.setDescribe(binding.edtDescribe.getText().toString());
        data.setYoutubeLink(binding.edtYoutubeLink.getText().toString());
        data.setImagePath(imagePath);
        data.setImageCase(imageCase);

        roomDB.dataDao().insert(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    Toast.makeText(this, "데이터 저장이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                    ((MainActivity)MainActivity.context).mainRecyclerRefresh();
                });
    }
}