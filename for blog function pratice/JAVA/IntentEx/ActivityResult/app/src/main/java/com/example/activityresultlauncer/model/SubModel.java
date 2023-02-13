package com.example.activityresultlauncer.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.example.activityresultlauncer.activity.SubActivity;

public class SubModel {

    private SubActivity subActivity;
    private Activity activity;

    public SubModel(SubActivity subActivity, Activity activity) {
        this.subActivity = subActivity;
        this.activity = activity;
    }

    public void sendData(String word){
        Intent intent = new Intent();
        intent.putExtra("word",word);
        activity.setResult(Activity.RESULT_OK,  intent);
        activity.finish();
    }
}
