package com.example.mvcpattern.model;

import com.example.mvcpattern.R;
import com.example.mvcpattern.controller.MainController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class ImageModel {

    private ArrayList<Integer> arrayList;
    private Random random;
    private MainController controller;

    public ImageModel(MainController controller) {
        this.controller = controller;
    }

    public void init(){
        arrayList = new ArrayList<>(Arrays.asList(R.drawable.baknana,R.drawable.djmax_clear_fail,R.drawable.djmax_falling_in_love
                ,R.drawable.mwama, R.drawable.tamtam));
        random = new Random();

        pickCard();
    }

    private void pickCard(){
        int select = random.nextInt(arrayList.size());

        controller.onCardPick(arrayList.get(select));
    }



}
