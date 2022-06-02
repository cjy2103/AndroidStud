package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CombinedVibration;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.os.VibratorManager;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // API 26 ~ 30
    private Vibrator vibrator;

    // API 31 ~
    private VibratorManager vibratorManager;
    private CombinedVibration combinedVibration;

    private VibrationEffect effect;
    private long [] timing = new long[]{100,200,100,200,100,200};
    private int [] amplitudes = new int[]{0,50,0,100,0,200};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibrateManagerCase();
        } else {
            vibrateCase();
        }
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            vibratorManager = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);
        } else {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void vibrateManagerCase(){
        binding.btnVibrate.setOnClickListener(v->{
            // 진동 발생
//            effect = VibrationEffect.createOneShot(100L,VibrationEffect.DEFAULT_AMPLITUDE);
//            combinedVibration = CombinedVibration.createParallel(effect);
//            vibratorManager.vibrate(combinedVibration);

            // 패턴 진동
//            effect = VibrationEffect.createWaveform(timing, -1); // -1 반복 X,  0 반복
//            combinedVibration = CombinedVibration.createParallel(effect);
//            vibratorManager.vibrate(combinedVibration);

            // 패턴(진동세기 조절) 진동 발생 (1회)
            effect = VibrationEffect.createWaveform(timing, amplitudes, -1);
            combinedVibration = CombinedVibration.createParallel(effect);
            vibratorManager.vibrate(combinedVibration);
            
//            vibratorManager.cancel(); // 진동 취소
        });
    }

    private void vibrateCase(){
        binding.btnVibrate.setOnClickListener(v->{
            // 진동 발생
//        effect = VibrationEffect.createOneShot(
//                50 , VibrationEffect.DEFAULT_AMPLITUDE);
//        vibrator.vibrate(effect); // 50ms 동안 진동
            // 패턴 진동
//        effect = VibrationEffect.createWaveform(timing, -1); // -1 반복 X,  0 반복
//        vibrator.vibrate(effect);

            // 패턴(진동세기 조절) 진동 발생 (1회)
            effect = VibrationEffect.createWaveform(timing, amplitudes,-1); // -1 반복 X,  0 반복
            vibrator.vibrate(effect);
            
//            vibrator.cancel(); // 진동 취소
        });
    }


}