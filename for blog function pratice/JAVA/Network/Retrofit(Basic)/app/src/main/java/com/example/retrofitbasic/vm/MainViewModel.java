package com.example.retrofitbasic.vm;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitbasic.repository.MainRepository;

public class MainViewModel {
    private MutableLiveData<String> data;

    private MainRepository mainRepository = new MainRepository();

    public MutableLiveData<String> getData() {
        if(data==null){
            data = new MutableLiveData<>();
            data.setValue("데이터 들어오는 부분");
        }
        return data;
    }

    public void dataLoad(){
        mainRepository.init(this);
    }

    public void dataResponse(String result){
        data.setValue(result);
    }

}
