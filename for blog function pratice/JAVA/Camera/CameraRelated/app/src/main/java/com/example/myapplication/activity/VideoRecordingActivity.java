package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.myapplication.databinding.ActivityVideoRecordingBinding;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class VideoRecordingActivity extends AppCompatActivity {

    private ActivityVideoRecordingBinding binding;

    private Context mContext;
    private ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture;
    private ImageCapture imageCapture;
    private VideoCapture videoCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickVideo();
    }

    private void viewBinding(){
        binding = ActivityVideoRecordingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        mContext = this;

        cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);

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

    @SuppressLint("RestrictedApi")
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        Preview preview = new Preview.Builder().build();

        preview.setSurfaceProvider(binding.previewViedo.getSurfaceProvider());

        videoCapture = new VideoCapture.Builder()
                .setVideoFrameRate(30)
                        .build();


        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    private void clickVideo(){
        binding.btnRecord.setOnClickListener(v->{
            if(videoCapture != null){
                long timestamp = System.currentTimeMillis();
                ContentValues contentValues = new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
            }
        });
    }
}