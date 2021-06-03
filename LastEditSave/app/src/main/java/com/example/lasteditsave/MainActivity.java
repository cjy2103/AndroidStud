package com.example.lasteditsave;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText test;
    String temp;
    SharedPreferences sf;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = (EditText) findViewById(R.id.test);

        sf = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

        if(sf.contains("lastKeyWord")){
            temp = sf.getString("lastKeyWord","");
        }

        test.setText(temp);

        test.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if(EditorInfo.IME_ACTION_SEARCH == actionId) {
                    temp = test.getText().toString();
                    Toast.makeText(MainActivity.this,temp,Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences.Editor edit = sf.edit();



        if(TextUtils.isEmpty(test.getText())) {
            Log.v("마지막순간","공백");
            edit.putString("lastKeyWord",temp);
        }
        else{
            edit.putString("lastKeyWord",temp);
            Log.v("마지막순간", temp);
        }
        edit.commit();
    }
}