package com.example.mvvm.activity.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private MutableLiveData<String> image;

    public MutableLiveData<String> getImage() {
        if(image == null){
            image = new MutableLiveData<>();
        }
        return image;
    }
}
