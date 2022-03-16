package com.example.wifimobilecheck;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;

public class NetWorkStatus {
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 3;


    /**
     * @DESC: API 29 이전에 사용했던 기존방식
     * @param context
     * @return
     */
    public static int getConnectStatusAsIs(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if(networkInfo != null){
            int type = networkInfo.getType();
            if(type == ConnectivityManager.TYPE_MOBILE){
                return TYPE_MOBILE;
            }else if(type == ConnectivityManager.TYPE_WIFI){
                return TYPE_WIFI;
            }
        }
        return TYPE_NOT_CONNECTED;  //연결이 되지않은 상태
    }

    public static int getConnectStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network network = manager.getActiveNetwork();
        if(network != null){
            NetworkCapabilities networkCapabilities  = manager.getNetworkCapabilities(network);
            if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                return TYPE_WIFI;
            } else if(networkCapabilities.hasTransport(NetworkCapabilities.))
        }
    }

}
