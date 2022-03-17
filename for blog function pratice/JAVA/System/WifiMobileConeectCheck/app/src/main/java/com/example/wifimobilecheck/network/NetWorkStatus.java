package com.example.wifimobilecheck.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;


public class NetWorkStatus {
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_MOBILE = 2;
    public static final int TYPE_NOT_CONNECTED = 3;


    /**
     * @DESC: 기존에 있던 방식 (Deprecated 됨)
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

    /**
     * @DESC :네트워크 상태
     * @param context
     * @return
     */
    public static int getConnectStatus(Context context){
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network = manager.getActiveNetwork();
            if(network != null){
                NetworkCapabilities networkCapabilities  = manager.getNetworkCapabilities(network);
                if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                    return TYPE_WIFI;
                } else if(networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                    return TYPE_MOBILE;
                }
            }
        } else {
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if(networkInfo != null){
                int type = networkInfo.getType();
                if(type == ConnectivityManager.TYPE_MOBILE){
                    return TYPE_MOBILE;
                }else if(type == ConnectivityManager.TYPE_WIFI){
                    return TYPE_WIFI;
                }
            }
        }
        return TYPE_NOT_CONNECTED;
    }

}
