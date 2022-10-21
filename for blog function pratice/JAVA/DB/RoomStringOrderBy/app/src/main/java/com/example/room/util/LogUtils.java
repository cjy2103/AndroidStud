package com.example.room.util;

import android.util.Log;

import com.example.room.BuildConfig;

public class LogUtils {
    public static void log(String x){
        if(BuildConfig.DEBUG)
            Log.i("RoomDB",x);
    }

    public static void log(int x){
        if(BuildConfig.DEBUG)
            Log.i("RoomDB",String.valueOf(x));
    }

    public static void log(String tag, String x){
        if(BuildConfig.DEBUG)
            Log.i(tag, x);
    }

    public static void log(String tag, int x){
        if(BuildConfig.DEBUG)
            Log.i(tag, String.valueOf(x));
    }
    public static void longlog(String s){
        final int MAX_LEN = 4000; // 2000 bytes 마다 끊어서 출력
        int len = s.length();
        if(len > MAX_LEN) {
            int idx = 0, nextIdx = 0;
            while(idx < len) {
                nextIdx += MAX_LEN;
                LogUtils.log(s.substring(idx, Math.min(nextIdx, len)));
                //Log.e(TAG, s.substring(idx, nextIdx > len ? len : nextIdx));
                idx = nextIdx;
            }
        } else {
            LogUtils.log(s);
            //Log.e(TAG, mFuncTag, s);
        }
    }
}
