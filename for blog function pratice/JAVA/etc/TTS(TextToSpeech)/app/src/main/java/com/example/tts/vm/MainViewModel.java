package com.example.tts.vm;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import static android.speech.tts.TextToSpeech.ERROR;

import androidx.databinding.ObservableField;

import java.util.Locale;

public class MainViewModel {

    public ObservableField<String> word = new ObservableField<>();
    private TextToSpeech tts;

    public void init(Context context){
        tts = new TextToSpeech(context, status -> {
           if(status != ERROR){
                tts.setLanguage(Locale.KOREA);
           }
        });
    }

    public void normalSpeech(){
        
    }

    public void highSpeech(){

    }

    public void lowerSpeech(){

    }

    public void fastSpeech(){

    }

    public void slowSpeech(){

    }
}
