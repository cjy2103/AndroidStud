package com.example.room.activity;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.room.room.Data;
import com.example.room.room.RoomDB;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainModel {
    private RoomDB roomDB;
    private MainActivity mainActivity;
    private Context context;
    private ArrayList<Data> carList;
    private int compareNum =0;

    public MainModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        context = mainActivity;
        carList = new ArrayList<Data>();
        roomDB = RoomDB.getInstance(context);
    }


    public void addList(){
        purchaseCar("스포티지","3400");
        purchaseCar("벤츠","12000");
        purchaseCar("카이엔","16000");
        purchaseCar("아반떼","2500");

        insertData();
    }

    private void purchaseCar(String name, String price){
        Data data = new Data();
        data.setCar(name);
        data.setPrice(price);
        carList.add(data);
    }

    @SuppressLint("CheckResult")
    private void insertData(){
        for(int i=0;i<carList.size();i++) {
            roomDB.dataDao().insert(carList.get(i)).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        compareNum +=1;
                        if(compareNum == carList.size()) {
                            mainActivity.connectAdapter(carList);
                        }
                    });
        }
    }

    @SuppressLint("CheckResult")
    public void sortData(){
        carList.clear();
        roomDB.dataDao().getSortAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item->{
                    carList.addAll(item);
                    mainActivity.connectAdapter(carList);
                });
    }


}
