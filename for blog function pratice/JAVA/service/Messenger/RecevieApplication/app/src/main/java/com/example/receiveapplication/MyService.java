package com.example.receiveapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {

    private Messenger mMessenger = new Messenger(new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    // A 애플리케이션으로부터 받은 메시지 처리
                    Toast.makeText(getApplicationContext(), "Message from A!", Toast.LENGTH_SHORT).show();
                    // A 애플리케이션으로 응답 메시지 전송
                    try {
                        msg.replyTo.send(Message.obtain(null, 2));
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    });

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Messenger를 반환하여 클라이언트가 서비스와 통신할 수 있도록 함
        return mMessenger.getBinder();
    }
}