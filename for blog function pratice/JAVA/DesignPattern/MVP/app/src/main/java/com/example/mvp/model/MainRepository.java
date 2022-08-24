package com.example.mvp.model;

import com.example.mvp.IPresenter;
import com.example.mvp.IRepository;
import com.example.mvp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainRepository implements IRepository {

    private ArrayList<Integer> arrayList;
    private Random random;

    private IPresenter presenter;

    public MainRepository(IPresenter presenter) {
        this.presenter = presenter;
        arrayList = new ArrayList<>(Arrays.asList(R.drawable.baknana
                , R.drawable.djmax_clear_fail,R.drawable.djmax_falling_in_love
                , R.drawable.mwama, R.drawable.tamtam));
        random = new Random();
    }

    @Override
    public void pickNewCard() {
        int select = random.nextInt(arrayList.size());
        presenter.onPickCard(arrayList.get(select));
    }

}
