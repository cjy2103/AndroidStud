package com.example.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.activitylifecycle.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private static String TAG = "MainActivity";
    private Context mContext;

    /***************************************************************
     ************************** 생명주기 ****************************
     **************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initiailize();
        
        moveActivity();

        Log.v(TAG, "생명주기 : onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "생명주기 : onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TAG, "생명주기 : onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TAG, "생명주기 : onPause");
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
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "생명주기 : onDestroy@");
    }

    /*****************************************************************
     ************************** 사용자 함수 ****************************
     *****************************************************************/

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initiailize(){
        mContext = MainActivity.this;
    }

    /**
     * @DESC: 액티비티 이동
     */
    private void moveActivity(){
        binding.btnMove.setOnClickListener(v->{
            Intent intent = new Intent(mContext, SubActivity.class);
            startActivity(intent);
        });
    }



}