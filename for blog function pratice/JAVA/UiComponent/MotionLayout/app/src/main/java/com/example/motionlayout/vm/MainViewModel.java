package com.example.motionlayout.vm;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;

import com.bumptech.glide.Glide;
import com.example.motionlayout.R;

public class MainViewModel implements MotionLayout.TransitionListener {

    public MutableLiveData<Integer> image;

    public MutableLiveData<Integer> getImage() {
        if(image == null){
          image = new MutableLiveData<>();
          image.setValue(R.drawable.baknana);
        }
        return image;
    }

    @Override
    public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

    }

    @Override
    public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
        if(startId == R.id.start && endId == R.id.end){
            if(progress < 0.5) {
                image.setValue(R.drawable.baknana);
                Log.v("이거탐?","11111");
            } else {
                image.setValue(R.drawable.djmax_clear_fail);
                Log.v("이거탐?","22222");
            }
        }
    }

    @Override
    public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {

    }

    @Override
    public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

    }

    @BindingAdapter("app:srcCompat")
    public static void setImage(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

}
