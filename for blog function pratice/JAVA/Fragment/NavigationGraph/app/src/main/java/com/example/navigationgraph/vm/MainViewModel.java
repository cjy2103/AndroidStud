package com.example.navigationgraph.vm;

import androidx.lifecycle.MutableLiveData;

import com.example.navigationgraph.data.ItemIconRes;

public class MainViewModel {

    private MutableLiveData<ItemIconRes> itemIconRes = new MutableLiveData<>();

    public MutableLiveData<ItemIconRes> getItemIconRes() {
        return itemIconRes;
    }

    public void setItemIconRes(int itemId, int iconResId){
        itemIconRes.setValue(new ItemIconRes(itemId, iconResId));
    }
}
