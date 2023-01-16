package com.example.mvvmintent.vm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

public class SubViewModel {
    private MutableLiveData<String> name = new MutableLiveData<>();
    private String str;
    private MutableLiveData<Integer> number = new MutableLiveData<>();
    private int num;

    public MutableLiveData<String> getName() {
        if(name == null){
            name = new MutableLiveData<>();
            name.setValue(str);
        }
        return name;
    }

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(num);
        }
        return number;
    }

    public void init(Context context){
        Activity activity = (Activity) context;
        Intent intent = activity.getIntent();
        str = intent.getStringExtra("String");
        num = intent.getIntExtra("int",0);
        name.setValue(str);
        number.setValue(num);
    }
}
