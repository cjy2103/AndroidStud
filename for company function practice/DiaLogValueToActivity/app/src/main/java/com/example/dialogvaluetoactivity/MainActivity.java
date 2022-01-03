package com.example.dialogvaluetoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements ExampleDialog.ExampleDialogListener{

    Button btn_dialog;
    TextView txt_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_test = findViewById(R.id.txt_test);

        btn_dialog = findViewById(R.id.btn_diaLog);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }

    /**
     * @DESC : 다이어로그 오픈
     */
    public void openDialog(){
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"example dialog");

    }

    @Override
    public void applyTexts(String tmpStr) {
        if(tmpStr=="") {
            txt_test.setText("Hello World!");
        } else {
            txt_test.setText(tmpStr);
        }
    }

}