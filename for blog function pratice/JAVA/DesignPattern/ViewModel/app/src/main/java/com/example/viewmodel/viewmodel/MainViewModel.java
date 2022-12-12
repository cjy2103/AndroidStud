package com.example.viewmodel.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.viewmodel.R;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    private final ArrayList<String> imageList = new ArrayList<>();
    private final ArrayList<String> nameList  = new ArrayList<>();

    private final Application application;

    public int num = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        init();
    }

    private void init(){
        String imageUri = "drawable://";
        imageList.add(imageUri + R.drawable.baknana);
        imageList.add(imageUri + R.drawable.mwama);
        imageList.add(imageUri + R.drawable.tamtam);

        nameList.add(application.getResources().getString(R.string.baknana));
        nameList.add(application.getResources().getString(R.string.mwamwa));
        nameList.add(application.getResources().getString(R.string.tamtam));
    }

    public Uri changeImage(){
        int pos   = num % 3;
        return Uri.parse("android.resource://" + application.getPackageName() + "/" + imageList.get(pos));
    }

    public String changeTitle(){
        int pos   = num % 3;
        return nameList.get(pos);
    }

}
