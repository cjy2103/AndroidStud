package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityPhotoResultBinding;
import com.example.myapplication.util.LogUtil;

import java.io.File;

public class PhotoResultActivity extends AppCompatActivity {

    private ActivityPhotoResultBinding binding;
    String imagePath = null;
    String realPath = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        clickOk();

        clickDelete();
    }

    private void viewBinding(){
        binding = ActivityPhotoResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        imagePath = getIntent().getStringExtra("photoUri");
        realPath = getIntent().getStringExtra("realPath");
        LogUtil.log("실 저장 위치"+realPath);
        if(imagePath!=null) {
            Uri photoUri = Uri.parse(imagePath);
            Glide.with(this).load(photoUri).into(binding.ivPhoto);
        }
    }

    private void clickOk(){
        binding.btnOk.setOnClickListener(v->{
            finish();
        });
    }

    private void clickDelete(){
        binding.btnDelete.setOnClickListener(v->{
            if(imagePath != null){
                Uri photoUri = Uri.parse(imagePath);
                File photoFile = new File(getIntent().getStringExtra("realPath"));
                LogUtil.log("사진 경로?"+photoFile);
                if (photoFile.exists()) {
                    if (photoFile.delete()) {
                        Toast.makeText(this, "사진이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "사진 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "파일이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
//                if(deleteImageFile(photoUri)){
//                    Toast.makeText(this,"사진 삭제 완료", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    Toast.makeText(this,"사진 삭제 실패", Toast.LENGTH_SHORT).show();
//                }
            }
        });
    }

    private boolean deleteImageFile(Uri fileUri){
        File fileToDelete = new File(fileUri.getPath());
        return fileToDelete.delete();
    }
}