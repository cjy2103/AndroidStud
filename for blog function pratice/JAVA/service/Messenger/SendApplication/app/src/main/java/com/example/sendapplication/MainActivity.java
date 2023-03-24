package com.example.sendapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.example.sendapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // Messenger 객체
    private Messenger mServiceMessenger = null;

    // service 연결 객체
    private ServiceConnection mServiceConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        init();
        clickSend();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        unbindService(mServiceConnection);
//    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mServiceMessenger = new Messenger(service);
                Log.v("ㅁㅁㅁ","ㄷㄷㄷ");
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                mServiceMessenger = null;
                Log.v("ㅁㅁㅁ","ㅁㅁㅁ");
            }
        };

        if(mServiceConnection == null){
            Log.v("ㅁㅁㅁ","초기화가 안되네?");
        } else{
            Log.v("ㅁㅁㅁ","초기화 되었는데?");
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.example.receiveapplication", "com.example.receiveapplication.MyService"));
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private void clickSend(){
        binding.btnSend.setOnClickListener(v->{
            Message msg = Message.obtain(null, 1);
            try {
                mServiceMessenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        });
    }
}