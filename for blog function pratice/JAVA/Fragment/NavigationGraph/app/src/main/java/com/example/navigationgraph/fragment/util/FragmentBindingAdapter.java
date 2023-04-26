package com.example.navigationgraph.fragment.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;

public class FragmentBindingAdapter {
    @BindingAdapter("setImage")
    public static void setImage(ImageView imageView, int imageResourceId){
        Drawable drawable = ContextCompat.getDrawable(imageView.getContext(),imageResourceId);
        imageView.setImageDrawable(drawable);
    }
}
