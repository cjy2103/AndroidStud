package com.example.mvp.presenter;

import com.example.mvp.IPresenter;
import com.example.mvp.IView;
import com.example.mvp.model.MainRepository;

public class MainPresenter implements IPresenter {

    private IView view;
    private MainRepository repository;

    public MainPresenter(IView view) {
        this.view = view;
        repository = new MainRepository(this);
    }

    @Override
    public void pickCard() {
        repository.pickNewCard();
    }

    @Override
    public void onPickCard(int pickImage) {
        view.pickCard(pickImage);
    }
}
