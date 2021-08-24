package com.example.spinnertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtInfo, txtContents;
    Spinner spinner;

    Integer [] items = {0,1,2,3,4,5};
//    String [] items = {"사과","딸기"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        txtInfo = findViewById(R.id.txt_info);
        txtContents = findViewById(R.id.txt_content);

        char defalut = 'C';

        Log.d("char값은:?:",(int)defalut+"");


        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(MainActivity.this,R.layout.spinner_item,items);

        adapter.setDropDownViewResource(R.layout.spinner_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                txtInfo.setText("선택된 값:"+items[position]);
                txtContents.setText("변환된 값:"+(char)(defalut+items[position]));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txtContents.setText("디폴트 값은:"+defalut);
            }
        });


    }
}