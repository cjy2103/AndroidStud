package com.example.receiveapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyService extends Service {

    private Messenger mMessenger;

    public void onCreate() {
        super.onCreate();
        mMessenger = new Messenger(new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case 1:
                        // A 애플리케이션으로부터 받은 메시지 처리
//                    Toast.makeText(getApplicationContext(), "Message from A!", Toast.LENGTH_SHORT).show();
                        Log.v("B애플리캐이션","응답이왔다.");
                        break;
                    default:
                        super.handleMessage(msg);
                }
            }
        });
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotificationChannel channel = new NotificationChannel("1", "My Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("My Service Channel");

        // 채널 등록
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

        Notification notification = new Notification.Builder(this, "1")
                .setContentTitle("MyService is running")
                .setContentText("Tap to open app")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        startForeground(1, notification);

        Log.v("B애플리캐이션","서비스 실행.");

        // do your work here

        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        // Messenger를 반환하여 클라이언트가 서비스와 통신할 수 있도록 함
        return mMessenger.getBinder();
    }
}