package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.normal.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private PermissionListener permissionlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        cameraPermission();

        clickPicture();

        clickVideo();
    }


    private void clickPicture(){
        binding.btnPicture.setOnClickListener(v->{
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        });
    }

    private void clickVideo(){
        binding.btnVideo.setOnClickListener(v->{

        });
    }



    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionDenied(List<String> deniedPermissions) {
                Toast.makeText(MainActivity.this
                        , "Permission Denied\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void cameraPermission(){
        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        checkPermission(Manifest.permission.CAMERA);
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