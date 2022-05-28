package com.example.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityCameraBinding;
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

        Preview preview = new Preview.Builder().build();

        preview.setSurfaceProvider(binding.previewPhoto.getSurfaceProvider());

        imageCapture = new ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build();

        cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);
    }

    private void clickPhoto(){
        binding.btnPhoto.setOnClickListener(v->{
            capturePhoto();
        });
    }

    private void capturePhoto(){
        // Android OS 10 이상 부터는 아래 코드 작동 불가
//        (사유 : Google에서 더이상 외부저장소 파일 생성 X MedioStore를 이용해서 생성하라고 함.)
//        File photoDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//        File saveDir = new File(photoDir, "CameraXDir");
//
//        if(!saveDir.exists()){
//            saveDir.mkdir();
//        }
//
//        Date date = new Date();
//        String timestamp = String.valueOf(date.getTime());
//        String photoFilePath = saveDir.getAbsolutePath() + "/" + timestamp + ".jpg";
//
//        File photoFile = new File(photoFilePath);

        long timeStamp = System.currentTimeMillis();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, timeStamp);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");

        imageCapture.takePicture(
//                new ImageCapture.OutputFileOptions.Builder(photoFile).build(), // Android X 부터 사용 불가
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
                    }

                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Toast.makeText(mContext, "사진 저장에 실패했습니다 사유 : " +
                                exception.getMessage(), Toast.LENGTH_SHORT).show();
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