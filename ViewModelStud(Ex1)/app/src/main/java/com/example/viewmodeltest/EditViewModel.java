package com.example.viewmodeltest;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EditViewModel extends ViewModel {
    private MutableLiveData<String> curretEdit;

    public MutableLiveData<String> getCurretEdit(){
        if(curretEdit == null){
            curretEdit = new MutableLiveData<String>();
        }
        return curretEdit;
    }

}
