package com.example.livedata.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.livedata.R;

import java.util.ArrayList;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<String> name;
    private MutableLiveData<Uri> image;

    private final ArrayList<String> imageList = new ArrayList<>();
    private final ArrayList<String> nameList  = new ArrayList<>();

    private final Application application;

    public int num = 0;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        init();
    }

    public MutableLiveData<String> getName() {
        if(name == null){
            name = new MutableLiveData<>();
        }
        return name;
    }

    public MutableLiveData<Uri> getImage() {
        if(image == null){
            image = new MutableLiveData<>();
        }
        return image;
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

    public void changeImage(){
        int pos   = num % 3;
        image.setValue(Uri.parse("android.resource://" + application.getPackageName() + "/" + imageList.get(pos)));
    }

    public void changeName(){
        int    pos  = num % 3;
        name.setValue(nameList.get(pos));
    }
}
