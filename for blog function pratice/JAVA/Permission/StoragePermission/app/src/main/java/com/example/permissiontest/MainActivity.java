package com.example.permissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.example.permissiontest.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;
    private Activity mActivity;
    private SharedPreferences sharedPreferences;
    private boolean permissionState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();

        initialize();

        storagePermissionCheck();

        clickStoragePermission();

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
        mActivity = MainActivity.this;

        sharedPreferences = mActivity.getSharedPreferences("StoragePermission",MODE_PRIVATE);
    }

    /**
     * @DESC: 저장소 권한 확인
     */
    private void storagePermissionCheck(){
        int writePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int readPermission  = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);

        if(writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED){
            binding.tvStorageStatus.setText("저장소 상태: 권한 부여되지 않음");
        } else {
            binding.tvStorageStatus.setText("저장소 상태: 권한 부여됨");
        }
    }

    /**
     * @DESC: 저장소 권한 요청
     */
    private void clickStoragePermission(){
        binding.btnStoragePermission.setOnClickListener(v->{

            permissionState = sharedPreferences.getBoolean("PermissionState",true);
    
            if(permissionState) {
                int writePermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
                int readPermission = ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);

                if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE}, 2002);
                    }
                } else {
                    Toast.makeText(mContext, "이미 권한이 부여됨", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(mContext, "권한이 거절되어 있음", Toast.LENGTH_SHORT).show();
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
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions,
                                           @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 2002){
            boolean checkResult = true;

            for(int result : grantResults){
                if(result != PackageManager.PERMISSION_GRANTED){
                    checkResult = false;
                    break;
                }
            }
            if(checkResult){
                binding.tvStorageStatus.setText("저장소 상태: 권한 부여됨");

            } else {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("PermissionState",false);
                editor.apply();

                Toast.makeText(mContext,"권한부여거절",Toast.LENGTH_SHORT).show();
            }
        }
    }
}