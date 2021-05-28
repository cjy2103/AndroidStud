package com.example.audiopermissiontest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn, btn2;
    private TextView textView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.d("Test", "녹음권한이 있습니다");
                    Toast.makeText(MainActivity.this,"녹음권한이 있습니다",Toast.LENGTH_SHORT).show();
                }

                if (checkSelfPermission(Manifest.permission.RECORD_AUDIO)
                        == PackageManager.PERMISSION_DENIED) {
                    Log.d("Test", "녹음권한이 없습니다");
                    Toast.makeText(MainActivity.this,"녹음권한이 없습니다",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void checkPermission() {
        TedPermission.with(this)
                .setRationaleTitle(R.string.permission_title)
                .setRationaleMessage(R.string.permission_message)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(
                        Manifest.permission.RECORD_AUDIO
                )
                //.setPermissionListener(permissionlistener).check();

                .setPermissionListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        // 기능수행

                        Toast.makeText(MainActivity.this,"권한이 허용되었습니다",Toast.LENGTH_SHORT).show();
                    }

                    //TODO 저장소 권한을 받지 않았더라도 songListActivity로 넘어가게끔 해야할듯.
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {

                        textView.setText("Please give permission in settings");
                    }
                }).check();
    }




}