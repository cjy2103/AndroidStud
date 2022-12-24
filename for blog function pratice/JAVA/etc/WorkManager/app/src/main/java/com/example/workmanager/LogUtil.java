package com.example.workmanager;

import android.util.Log;

public class LogUtil {
    public static void log(String x){
        if(BuildConfig.DEBUG)
            Log.i("LogUtil",x);
    }

    public static void log(int x){
        if(BuildConfig.DEBUG)
            Log.i("LogUtil",String.valueOf(x));
    }

    public static void log(String tag, String x){
        if(BuildConfig.DEBUG)
            Log.i(tag, x);
    }

    public static void log(String tag, int x){
        if(BuildConfig.DEBUG)
            Log.i(tag, String.valueOf(x));
    }
}
