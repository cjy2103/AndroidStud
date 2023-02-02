package com.example.myapplication.vm;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.R;

public class MainViewModel {
    private MutableLiveData<Integer> string;

    public MutableLiveData<Integer> getString() {
        if(string == null) {
            string = new MutableLiveData<>();
            string.setValue(R.string.hello);
        }
        return string;
    }

    public void textChange(){
        string.setValue(R.string.message);
    }
}
