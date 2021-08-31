package com.example.numberpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.shawnlin.numberpicker.NumberPicker;


public class MainActivity extends AppCompatActivity {

    NumberPicker numberPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberPicker = findViewById(R.id.horizontal_number_picker);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                Log.v("선택된 값은?","newVal:"+newVal);
            }
        });

    }
}