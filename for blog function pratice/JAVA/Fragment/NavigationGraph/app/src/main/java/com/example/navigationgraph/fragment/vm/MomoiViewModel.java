package com.example.navigationgraph.fragment.vm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.navigationgraph.R;

public class MomoiViewModel extends ViewModel {

    private MutableLiveData<Integer> image;

    public MutableLiveData<Integer> getImage() {
        if(image == null){
            image = new MutableLiveData<>();
            image.setValue(R.drawable.iv_momoi);
        }
        return image;
    }


    private int currentIndex = 0;

    public void changeImage(){
        currentIndex++;
        if(currentIndex % 2 == 0){
            image.setValue(R.drawable.iv_momoi);
        } else {
            image.setValue(R.drawable.iv_momoi_stand);
        }
    }
    
}
