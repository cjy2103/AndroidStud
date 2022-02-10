package com.example.runonuithreadclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.runonuithreadclock.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        timerSetting();

    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void timerSetting(){
        runnable = () -> {
            Calendar calendar = Calendar.getInstance();

            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat
                    = new SimpleDateFormat("HH:mm:ss");
            String strTime = simpleDateFormat.format(calendar.getTime());

            binding.tvTime.setText(strTime);
        };

        NewRunnable newRunnable = new NewRunnable() ;
        Thread thread = new Thread(newRunnable);
        thread.start();
    }

    class NewRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace() ;
                }

                // 메인 스레드에 runnable 전달.
                runOnUiThread(runnable) ;
            }
        }
    }
}