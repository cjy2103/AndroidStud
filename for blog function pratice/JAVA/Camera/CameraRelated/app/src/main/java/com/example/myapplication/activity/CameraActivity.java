package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaActionSound;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCameraBinding;
import com.example.myapplication.util.LogUtil;
import com.example.myapplication.util.SystemUtil;
import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class CameraActivity extends AppCompatActivity {

    private ActivityCameraBinding binding;

    private Context mContext;
    private ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture;
    private ImageCapture imageCapture;
    private SystemUtil systemUtil;
    private Camera cam;
    private MediaActionSound sound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickPhoto();

        clickCancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraProviderListenableFuture = null;
        imageCapture = null;
    }

    private void viewBinding(){
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        mContext = this;
        systemUtil = new SystemUtil();
        systemUtil.sofNavigationBarHide(getWindow());

        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);

        sound = new MediaActionSound();

        cameraAddListener();
    }

    private void cameraAddListener(){
        cameraProviderListenableFuture.addListener(()->{
            try {
                ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }, getMainExecutor());
    }

    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();
//
//        CameraSelector cameraSelector = new CameraSelector.Builder()
//                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
//                .build();

        Preview preview = new Preview.Builder().build();

        preview.setSurfaceProvider(binding.previewPhoto.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        cam = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

    }

    private void clickPhoto(){
        binding.btnPhoto.setOnClickListener(v->{
            capturePhoto();
        });
    }

    private void capturePhoto(){
        long timeStamp = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timeStamp);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

        imageCapture.takePicture(
                new ImageCapture.OutputFileOptions.Builder(
                          getContentResolver()
                        , MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        , contentValues
                        ).build()
                , getMainExecutor()
                , new ImageCapture.OnImageSavedCallback(){
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                        Toast.makeText(mContext, "사진이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                        if(cam.getCameraInfo().hasFlashUnit()){
                            cam.getCameraControl().enableTorch(true);
                        }
//                        sound.play(MediaActionSound.SHUTTER_CLICK);
                        runOnUiThread(()->{
                            new Handler(Looper.myLooper()).postDelayed(() ->{
                                cam.getCameraControl().enableTorch(false);
                                Intent intent = new Intent(mContext, PreViewActivity.class);
                                intent.putExtra("photoUri", outputFileResults.getSavedUri().toString());
                                startActivity(intent);
                            },100);
                            LogUtil.log("사진 경로?"+outputFileResults);
//                            Intent intent = new Intent(mContext, PreViewActivity.class);

                        });


                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(mContext, "사진 저장에 실패했습니다 사유 : " +
                                exception.getMessage(), Toast.LENGTH_SHORT).show();
                        cam.getCameraControl().enableTorch(false);
                    }
                }
        );

    }

    private void clickCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }
}