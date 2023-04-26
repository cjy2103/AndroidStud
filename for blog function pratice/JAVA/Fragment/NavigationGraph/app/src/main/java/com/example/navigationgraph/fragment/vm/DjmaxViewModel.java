package com.example.navigationgraph.fragment.vm;

import androidx.lifecycle.MutableLiveData;

import com.example.navigationgraph.R;

public class DjmaxViewModel {

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
            image.setValue(R.drawable.iv_alice);
        }
    }
}
