package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.databinding.ActivityPhotoResultBinding;
import com.example.myapplication.util.LogUtil;

import java.io.File;

public class PhotoResultActivity extends AppCompatActivity {

    private ActivityPhotoResultBinding binding;
    String imagePath = null;


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
                ContentResolver contentResolver = getContentResolver();
                String[]        projection = {MediaStore.Images.Media.DATA};

                @SuppressLint("Recycle") Cursor cursor     = contentResolver.query(Uri.parse(imagePath), projection, null, null, null);
                if(cursor != null && cursor.moveToFirst()){
                    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    File file = new File(filePath);

                    if(file.delete()){
                        Toast.makeText(this, "사진이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, "사진 삭제에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }


}