package com.example.edittextthread.vm;

import android.graphics.Color;

import androidx.lifecycle.MutableLiveData;

public class MainViewModel {

    private MutableLiveData<String> showState = new MutableLiveData<>();
    private MutableLiveData<Integer> color = new MutableLiveData<>();

    private Thread thread;

    public MutableLiveData<String> getShowState() {
        if(showState == null){
            showState = new MutableLiveData<>();
        }
        return showState;
    }

    public MutableLiveData<Integer> getColor() {
        if(color == null){
            color = new MutableLiveData<>();
        }
        return color;
    }

    public void onTextChanged(CharSequence word, int start, int before, int count){
        showState.setValue("");
        color.setValue(Color.parseColor("#070707"));

        if(thread != null) {
            thread.interrupt();
        }

        thread = new Thread(()->{
           try{
               Thread.sleep(1500);
               showState.postValue("1.5초뒤 실행됨");
               color.postValue(Color.parseColor("#FD0000"));
           } catch (InterruptedException e){
               Thread.currentThread().interrupt();
           }
        });

        thread.start();
    }
}
