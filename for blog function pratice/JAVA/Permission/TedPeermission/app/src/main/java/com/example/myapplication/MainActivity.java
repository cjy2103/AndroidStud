package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;


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

        clickEvent();

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

    private void clickEvent(){
        permissionStorage();
        permissionMic();
        permissionCamera();
        permissionLocation();
    }

    private void permissionStorage(){
        binding.btnStorage.setOnClickListener(v->{
            checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        });
    }

    private void permissionMic(){
        binding.btnMic.setOnClickListener(v->{
            checkPermission(Manifest.permission.RECORD_AUDIO);
        });
    }

    private void permissionCamera(){
        binding.btnCamera.setOnClickListener(v->{
            checkPermission(Manifest.permission.CAMERA);
        });
    }

    private void permissionLocation(){
        binding.btnLocate.setOnClickListener(v->{
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION);
        });

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