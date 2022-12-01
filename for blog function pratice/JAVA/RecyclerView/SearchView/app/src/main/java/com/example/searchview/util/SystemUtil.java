package com.example.searchview.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
import android.view.WindowManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.searchview.R;


public class SystemUtil {

    /**
     * @DESC: 상단바 투명
     * @param window
     * @param context
     * @param constraintLayout
     */
    @SuppressLint("ObsoleteSdkInt")
    public static void statusbarSetting(Window window, Context context, ConstraintLayout constraintLayout){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            constraintLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // 아이콘색 검정으로 -> 주석하면 흰색
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(context, R.color.black));
        }
    }


    /**
     * @DESC: SoftBottomBar 숨기기
     * @param window
     */
    public static void sofNavigationBarHide(Window window){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.setDecorFitsSystemWindows(false);
            if(window.getInsetsController() != null){
                window.getInsetsController().hide(WindowInsets.Type.navigationBars());
                window.getInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
            }
        } else {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
