package com.example.pickertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener{

    private TextView tvShowNumber, tvNum;
    private ImageButton btnDown, btnUp;
    private int tmpNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> nums = new ArrayList<>();


        tvShowNumber = findViewById(R.id.txtShowNumber);
        NumberPicker numberPicker = findViewById(R.id.number_picker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(12);

        numberPicker.setFormatter(new NumberPicker.Formatter() {
            @Override
            public String format(int value) {
                return String.format("%02d",value);
            }
        });

        numberPicker.setOnValueChangedListener(this);

        tvNum = findViewById(R.id.tv_num);
        tmpNumber = Integer.parseInt((String) tvNum.getText());

        btnDown = findViewById(R.id.btn_down);

        btnDown.setOnClickListener(v->{
            if(tmpNumber<-11){
                tmpNumber=-12;
            } else {
                tmpNumber-=1;
                tvNum.setText(""+tmpNumber);
            }
        });

        btnUp = findViewById(R.id.btn_up);

        btnUp.setOnClickListener(v->{
            if(tmpNumber>11){
                tmpNumber = 12;
            } else {
                tmpNumber+=1;
                tvNum.setText(""+tmpNumber);
            }
        });





    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        tvShowNumber.setText("현재 선택한 값:"+newVal);
    }
}