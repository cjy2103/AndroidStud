package com.example.customcheckbox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn,saveNumber;
    Dialog dialog;
    SharedPreferences sf;
    CheckBox checkBox;
    int tmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        saveNumber = (Button) findViewById(R.id.saveNumber);

        dialog = new Dialog(MainActivity.this);

        sf = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if(sf.contains("ischeck")){
            tmp = sf.getInt("ischeck",0);
        }

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_dialog);


        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();

        display.getSize(size);

        Window window = dialog.getWindow();

        int x = (int)(size.x*0.9f);
        int y = (int)(size.y*0.25f);

        window.setLayout(x,y);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tmp!=1) {
                    showDialog();
                }
                else{
                    Toast.makeText(MainActivity.this,"이미 체크박스가 선택되어 있어 더이상 뜨지 않음",Toast.LENGTH_SHORT).show();
                }
            }
        });

        saveNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"이미 체크박스가 선택되어있어 더이상 뜨지 않음 tmp 값 :"+tmp,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


        SharedPreferences.Editor edit = sf.edit();
        //edit.putBoolean("ischeck",checkBox.isChecked());

        // Boolean 안되서 Int로 지정... 마지막 tmp 값을 저장
        edit.putInt("ischeck",tmp);

        edit.commit();

    }

    public void showDialog(){

            dialog.show(); // 다이어로그 띄우기



            dialog.findViewById(R.id.okButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            checkBox = dialog.findViewById(R.id.checkBox);

            dialog.findViewById(R.id.checkBox).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkBox.isChecked()){
                        Toast.makeText(MainActivity.this,"체크박스 선택",Toast.LENGTH_SHORT).show();
                        tmp = 1;
                    }
                    else {
                        Toast.makeText(MainActivity.this,"체크박스 미선택",Toast.LENGTH_SHORT).show();
                        tmp = 0;
                    }
                }
            });

    }
}