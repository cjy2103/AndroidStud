package com.example.retrofitbasic.vm;

import androidx.lifecycle.MutableLiveData;

import com.example.retrofitbasic.repository.RetrofitCallback;
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
        mainRepository.init(new RetrofitCallback(){
            @Override
            public void onSuccess(String reuslt) {
                data.setValue(reuslt);
            }

            @Override
            public void onFailed() {
                data.setValue("데이터 받아오기 실패");
            }

            @Override
            public void onError(Throwable throwable) {
                data.setValue("오류발생:"+throwable);
            }
        });
    }
}
