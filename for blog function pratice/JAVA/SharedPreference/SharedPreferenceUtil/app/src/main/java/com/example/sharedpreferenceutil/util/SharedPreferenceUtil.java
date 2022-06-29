package com.example.sharedpreferenceutil.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceUtil {

    /**
     * @DESC: String 데이터를 저장
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference
            (Context context, String key, String value)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(key, value);
        editor.apply();
    }

    /**
     * @DESC: boolean 데이터를 저장함
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference
            (Context context, String key, boolean value)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * @DESC: int 데이터를 저장함
     * @param context
     * @param key
     * @param value
     */
    public static void putSharedPreference
            (Context context, String key, int value)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * @DESC: String 데이터를 읽어옴
     * @param context
     * @param key
     * @return
     */
    public static String getSharedPreference
            (Context context, String key)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getString(key, null);
    }

    /**
     * @DESC: boolean 데이터를 읽어옴
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanSharedPreference
            (Context context, String key)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getBoolean(key, false);
    }

    /**
     * @DESC: int 데이터 값을 읽어옴
     * @param context
     * @param key
     * @return
     */
    public static int getIntSharedPreference
            (Context context, String key)
    {
        SharedPreferences prefs =
                PreferenceManager.getDefaultSharedPreferences(context);

        return prefs.getInt(key, 0);
    }
}
