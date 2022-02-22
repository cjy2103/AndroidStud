package com.example.galleryimageselect;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.galleryimageselect.databinding.ActivityMainBinding;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ActivityResultLauncher<Intent> resultLauncher;
    private static final int REQUEST_CODE = 2022;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        openGallery();

        galleryCallback();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 갤러리 열기
     */
    private void openGallery(){
        binding.btnSearch.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            resultLauncher.launch(intent);
//            startActivityForResult(intent,REQUEST_CODE); // deprecated
        });
    }

    /**
     * @DESC: 콜백함수
     */
    private void galleryCallback(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        try {
                            if(result.getData() == null){
                                return;
                            }
                            Uri uri = result.getData().getData();
                            Glide.with(this).load(uri).into(binding.ivImg);
                        } catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}