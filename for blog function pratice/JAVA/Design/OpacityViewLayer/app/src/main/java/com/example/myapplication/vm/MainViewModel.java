package com.example.myapplication.vm;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.myapplication.SubActivity;

public class MainViewModel {


    public void move(View view){
        Context context = view.getContext();
        Intent intent = new Intent(context, SubActivity.class);
        context.startActivity(intent);
    }

    public void fakeClick(){
        Log.v("리스너 가로채기", "아무것도 작동 안하지롱");
    }
}
