package com.example.navigationgraph.fragment.vm;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigationgraph.R;

public class DjmaxViewModel extends ViewModel {

    private MutableLiveData<Integer> image;

    public MutableLiveData<Integer> getImage() {
        if(image == null){
            image = new MutableLiveData<>();
            image.setValue(R.drawable.iv_djmax_x_mas);
        }
        return image;
    }


    private int currentIndex = 0;

    public void changeImage(){
        currentIndex++;
        if(currentIndex % 2 == 0){
            image.setValue(R.drawable.iv_djmax_x_mas);
        } else {
            image.setValue(R.drawable.iv_djmax_alice);
        }
    }
}
