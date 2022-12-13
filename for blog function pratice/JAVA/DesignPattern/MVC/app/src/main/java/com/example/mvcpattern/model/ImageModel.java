package com.example.mvcpattern.model;

import com.example.mvcpattern.R;
import com.example.mvcpattern.controller.MainController;
import com.example.mvcpattern.view.MainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ImageModel {

    private ArrayList<Integer> arrayList;
    private Random random;
//    private MainController controller;
    private MainActivity mainActivity;

    public ImageModel(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        init();
    }
//
//    public ImageModel(MainController controller) {
////        this.controller = controller;
//    }

    public void init(){
        arrayList = new ArrayList<>(Arrays.asList(R.drawable.baknana
                , R.drawable.djmax_clear_fail,R.drawable.djmax_falling_in_love
                , R.drawable.mwama, R.drawable.tamtam));
        random = new Random();

    }

    public void pickCard(){
        int select = random.nextInt(arrayList.size());
        mainActivity.imageUpdate(arrayList.get(select));
//        controller.onCardPick(arrayList.get(select));
    }
}
