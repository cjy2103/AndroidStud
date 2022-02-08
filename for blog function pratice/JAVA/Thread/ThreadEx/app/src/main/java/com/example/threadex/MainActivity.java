package com.example.threadex;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

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

        settingTimerRunnalbe();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 타이머 세팅
     */
    private void settingTimerRunnalbe(){
        handler = new Handler(Looper.getMainLooper()){
            Calendar calendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            String time = simpleDateFormat.format(calendar.getTime());
//            binding.tvTimer.setText(time);
        };

        NewRunnable newRunnable = new NewRunnable();
        Thread thread = new Thread(newRunnable);
        thread.start();
    }


    private class NewRunnable implements Runnable{
        @Override
        public void run() {
            while (true) {


                try {
                    Thread.sleep(1000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}