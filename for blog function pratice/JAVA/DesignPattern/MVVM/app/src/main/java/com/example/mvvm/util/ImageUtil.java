package com.example.mvvm.util;

import com.example.mvvm.R;

public class ImageUtil {

    public static String imageUri = "drawable://";
    public static String [][] imageTable = {
            {imageUri + R.drawable.baknana, "박나나"},
            {imageUri + R.drawable.djmax_clear_fail, "클리어,페일"},
            {imageUri + R.drawable.djmax_falling_in_love, "falling_in_love"},
            {imageUri + R.drawable.mwama, "뫄마"},
            {imageUri + R.drawable.tamtam, "탬탬"}
    };

    public static String imageSave(String imagePath){
        String key      = "";

        for(String[] images : imageTable){
            if(imagePath.equals(images[0])){
                key = images[1];
            }
        }
        return key;
    }

    public static String imageLoad(String key){
        String imagePath = "";

        for (String[] images : imageTable){
            if(key.equals(images[1])){
                imagePath = images[0];
            }
        }
        return imagePath;
    }

}
