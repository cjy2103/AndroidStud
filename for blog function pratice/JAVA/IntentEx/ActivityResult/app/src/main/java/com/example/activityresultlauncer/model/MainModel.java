package com.example.activityresultlauncer.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.example.activityresultlauncer.activity.MainActivity;
import com.example.activityresultlauncer.activity.SubActivity;

public class MainModel {

    private MainActivity mainActivity;
    private Context      context;

    private ActivityResultLauncher<Intent> resultLauncher;

    public MainModel(MainActivity mainActivity, Context context) {
        this.mainActivity = mainActivity;
        this.context      = context;
        callback();
    }

    private void callback() {
        resultLauncher = mainActivity.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        if (result.getData() == null) {
                            return;
                        }
                        String word = result.getData().getStringExtra("word");
                        mainActivity.callbackWord(word);
                    }
                });
    }

    public void moveSubActivity() {
        Intent intent = new Intent(context, SubActivity.class);
        resultLauncher.launch(intent);
    }
}
