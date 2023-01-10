package com.example.mvvm.activity.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.util.ImageUtil;

public class ImageViewModel extends ViewModel {
    private MutableLiveData<String> image;

    public MutableLiveData<String> getImage() {
        if(image == null){
            image = new MutableLiveData<>();
        }
        return image;
    }

    public void imagePick(){
        int value = (int) (Math.random() * ImageUtil.imageTable.length);
        image.setValue(ImageUtil.imageTable[value][0]);
    }
}
