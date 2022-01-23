package com.example.logexplanation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static String Tag = "태그는 여러분이 원하시는거 쓰시면 됩니다.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.v(Tag,"로그에는 여러 가지 종류가 있습니다. Log.v에서 v는 Verbos(말수가 많은) -> 중요하지 않은 정보");
        Log.i(Tag,"Log.i 에서 i는 info(정보)");
        Log.d(Tag,"Log.d에서 d는 debug(디버그)");
        Log.w(Tag,"Log.w에서 w는 warning(경고)");
        Log.e(Tag,"Log.e에서 e는 error(오류)입니다.");


    }
}