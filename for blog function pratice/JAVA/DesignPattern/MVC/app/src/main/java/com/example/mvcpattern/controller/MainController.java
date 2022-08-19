package com.example.mvcpattern.controller;

import com.example.mvcpattern.view.MainActivity;
import com.example.mvcpattern.model.ImageModel;

public class MainController {

    private MainActivity mainActivity; // View
    private ImageModel model;

    public MainController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        model = new ImageModel(this);
    }

    public void pickCard(){
        model.init();
    }

    public void onCardPick(Integer pickCard){
        this.mainActivity.binding.imageView.setImageResource(pickCard);
    }
}
