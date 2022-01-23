package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.activitylifecycle.databinding.ActivitySubBinding;

public class SubActivity extends AppCompatActivity {

    private ActivitySubBinding binding;

    private static String TAG = "SubActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        initBinding();

        closeActivity();

        Log.v(TAG, "생명주기 : onCreate");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "생명주기 : onStart");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "생명주기 : onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "생명주기 : onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "생명주기 : onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "생명주기 : onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "생명주기 : onDestroy");
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 창닫기
     */
    private void closeActivity(){
        binding.btnClose.setOnClickListener(v->{
            finish();
        });
    }
}