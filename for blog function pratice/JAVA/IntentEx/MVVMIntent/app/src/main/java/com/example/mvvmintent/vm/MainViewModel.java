package com.example.mvvmintent.vm;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import androidx.databinding.BindingAdapter;

import com.example.mvvmintent.view.SubActivity;

public class MainViewModel {

    public void dataSend(Button button){
        Activity activity = (Activity) button.getContext();
        Intent intent = new Intent(activity, SubActivity.class);
        intent.putExtra("String","테스트");
        intent.putExtra("int",1);
        activity.startActivity(intent);
    }
}
