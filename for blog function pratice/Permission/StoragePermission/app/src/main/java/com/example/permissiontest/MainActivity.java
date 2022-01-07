package com.example.permissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.permissiontest.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();

        initialize();

        clickStoragePermssion();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        mContext = MainActivity.this;
    }

    /**
     * @DESC: 저장소 권한 요청
     */
    private void clickStoragePermssion(){
        binding.btnStoragePermission.setOnClickListener(v->{
            int writePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readPermission  = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);

            if(writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED){
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},2022);
                }
            } else {
                Toast.makeText(mContext,"이미 권한이 부여됨",Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * @DESC: 권한요청 콜백
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 2022){
            boolean checkResult = true;

            for(int result : grantResults){
                if(result != PackageManager.PERMISSION_GRANTED){
                    checkResult = false;
                    break;
                }
            }
            if(checkResult){
                binding.tvStorageStatus.setText("저장소 권한 부여됨");

            } else {
                Toast.makeText(mContext,"권한부여거절",Toast.LENGTH_SHORT).show();
            }
        }
    }
}