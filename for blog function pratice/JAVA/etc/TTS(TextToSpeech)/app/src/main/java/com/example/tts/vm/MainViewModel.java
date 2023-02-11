package com.example.tts.vm;

import android.content.Context;
import android.os.Bundle;
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
        tts.setPitch(1.0f);
        tts.setSpeechRate(1.0f);
        tts.speak(word.get(), TextToSpeech.QUEUE_FLUSH, null,null);
    }

    public void highSpeech(){
        tts.setPitch(2.0f);
        tts.setSpeechRate(1.0f);
        tts.speak(word.get(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void lowerSpeech(){
        tts.setPitch(0.5f);
        tts.setSpeechRate(1.0f);
        tts.speak(word.get(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void fastSpeech(){
        tts.setPitch(1.0f);
        tts.setSpeechRate(2.0f);
        tts.speak(word.get(), TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void slowSpeech(){
        tts.setPitch(1.0f);
        tts.setSpeechRate(0.5f);
        tts.speak(word.get(), TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
