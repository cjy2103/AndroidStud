package com.example.workmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private WorkManager workManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        workManager = WorkManager.getInstance(this);

        workManager.cancelAllWork();

        oneTimeWorkRequest();
        periodWorkRequest(10);

        rxOneTimeWorkRequest();
        rxPeriodWorkRequest(10);
    }



    private void oneTimeWorkRequest(){
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(MyWorker.class).build();
        workManager.enqueue(request);
    }

    private void periodWorkRequest(int time){
        PeriodicWorkRequest request =
                            new PeriodicWorkRequest.Builder(MyWorker.class,time, TimeUnit.MINUTES).build();
        workManager.enqueue(request);

    }

    private void rxOneTimeWorkRequest() {
        OneTimeWorkRequest request =
                new OneTimeWorkRequest.Builder(MyRxWorker.class).build();
        workManager.enqueue(request);
    }

    private void rxPeriodWorkRequest(int time) {
        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(MyRxWorker.class,time, TimeUnit.MINUTES).build();
        workManager.enqueue(request);
    }


}