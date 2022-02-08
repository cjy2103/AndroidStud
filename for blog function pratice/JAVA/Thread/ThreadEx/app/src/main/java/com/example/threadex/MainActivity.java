package com.example.threadex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import com.example.threadex.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        settingTimerThread();

//        settingTimerRunnalbe();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC : Thread extends 방법
     */
    private void settingTimerThread(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat =
                        new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(calendar.getTime());
                binding.tvTimer.setText(time);
            }
        };

        NewThread newThread = new NewThread();
        newThread.start();

    }

    /**
     * @DESC: Runnable implents 방법
     */
    private void settingTimerRunnalbe(){
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Calendar calendar = Calendar.getInstance();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(calendar.getTime());
                binding.tvTimer.setText(time);
            }
        };

        NewRunnable newRunnable = new NewRunnable();
        Thread thread = new Thread(newRunnable);
        thread.start();

    }

    /**
     * @DESC: Runnable implements
     */
    private class NewRunnable implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }

    /**
     * @DESC: Thread extends
     */
    private class NewThread extends Thread{

        @Override
        public void run() {
            super.run();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }
    }
}