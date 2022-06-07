package com.example.roomrecyclerview.util;

import android.graphics.Typeface;
import android.widget.TextView;

public class LanguageCheck {

    /**
     * @DESC: 한글인지 체크
     * @param text
     * @return
     */
    private boolean checkKr(CharSequence text){
        String checkKr = (String) text;
        if(checkKr.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @DESC: 영어/한글일때 Typeface, size 조절
     * @param text
     * @param english
     * @param korea
     * @param eSize
     * @param kSize
     */
    public void checkLanguage(TextView text, Typeface english, Typeface korea, int eSize, int kSize){
        boolean isKr = checkKr(text.getText());
        if(!isKr){
            text.setTypeface(english);
            text.setTextSize(eSize);
        } else {
            text.setTypeface(korea);
            text.setTextSize(kSize);
        }
    }
}
