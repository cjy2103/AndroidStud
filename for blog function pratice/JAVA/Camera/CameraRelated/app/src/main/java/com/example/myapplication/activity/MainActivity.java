package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewbinding.ViewBinding;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.util.LogUtil;
import com.example.myapplication.util.SystemUtil;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;
import java.util.function.Function;

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    private PermissionListener permissionlistener;

    public MainActivity() {
        super(ActivityMainBinding::inflate);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hideStatusBar(binding.consMain);
        hideBottomBar();

        initialize();

        cameraPermission();

        clickPicture();

        clickVideo();
    }

    /******************************************************************************************
     ************************************ 이벤트 메서드 ****************************************
     *****************************************************************************************/


    private void clickPicture(){
        binding.btnPicture.setOnClickListener(v->{
            int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            int storage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(camera != PackageManager.PERMISSION_GRANTED
                    && storage != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "저장소, 카메라 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, CameraActivity.class);
                startActivity(intent);
            }
        });
    }

    private void clickVideo(){
        binding.btnVideo.setOnClickListener(v->{
            int audio = ContextCompat.checkSelfPermission(this, android.Manifest.permission.RECORD_AUDIO);
            int camera = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);
            int storage = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(camera != PackageManager.PERMISSION_GRANTED
                    && storage != PackageManager.PERMISSION_GRANTED
                    && audio != PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "카메라, 저장소, 오디오 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, VideoRecordingActivity.class);
                startActivity(intent);
            }
        });
    }

    /******************************************************************************************
     ************************************** 일반 메서드 ****************************************
     *****************************************************************************************/

    private void initialize(){

        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
//                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this
                        , "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
                LogUtil.log("권한"+ deniedPermissions);
            }
        };
    }

    private void cameraPermission(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            checkPermission(Manifest.permission.READ_MEDIA_IMAGES);
        } else {
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        checkPermission(Manifest.permission.CAMERA);
        checkPermission(Manifest.permission.RECORD_AUDIO);
    }

    private void checkPermission(String permission){
        TedPermission.create()
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n" +
                        "\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(permission)
                .check();
    }


}