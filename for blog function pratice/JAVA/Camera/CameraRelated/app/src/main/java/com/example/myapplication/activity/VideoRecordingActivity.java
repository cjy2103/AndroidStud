package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityVideoRecordingBinding;
import com.example.myapplication.util.LogUtil;
import com.example.myapplication.util.SystemUtil;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

public class VideoRecordingActivity extends AppCompatActivity {

    private ActivityVideoRecordingBinding binding;

    private Context mContext;
    private ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture;
    private ImageCapture imageCapture;
    private VideoCapture videoCapture;
    private SystemUtil systemUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickVideo();

        clickCancel();
    }

    private void viewBinding(){
        binding = ActivityVideoRecordingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        mContext = this;

        systemUtil = new SystemUtil();
        systemUtil.sofNavigationBarHide(getWindow());

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

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        videoCapture = new VideoCapture.Builder()
                .setVideoFrameRate(30)
                        .build();

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture, videoCapture);
    }


    @SuppressLint({"MissingPermission", "RestrictedApi"})
    private void clickVideo(){
        binding.btnRecord.setOnClickListener(v->{
            if(binding.btnRecord.getText().toString().equals("비디오 촬영")) {
                binding.btnRecord.setText("비디오 촬영 종료");
                if (videoCapture != null) {
                    long timestamp = System.currentTimeMillis();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timestamp);
                    contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");

                    videoCapture.startRecording(new VideoCapture.OutputFileOptions.Builder(
                                    getContentResolver()
                                    , MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                                    , contentValues

                            ).build()
                            , getMainExecutor()
                            , new VideoCapture.OnVideoSavedCallback() {
                                @Override
                                public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                                    Toast.makeText(mContext, "동영상이 정상적으로 저장되었습니다.", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                                    Toast.makeText(mContext, "사진 저장에 실패했습니다 사유 : " +
                                            message, Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else {
                binding.btnRecord.setText("비디오 촬영");
                videoCapture.stopRecording();
            }
        });

    }

    private void clickCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }
}