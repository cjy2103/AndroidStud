package com.example.edittexttest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtTest;
    boolean b_cursorVisible_edt_search= false;
    private int count =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtTest = findViewById(R.id.edt_test);

//        edtTest.setText("Hello");



        edtTest.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(MainActivity.this,"포오커스으",Toast.LENGTH_SHORT).show();

                edtTest.setOnClickListener(v2 -> {
                    if(count>0) {
                        b_cursorVisible_edt_search = !b_cursorVisible_edt_search;
                        if (b_cursorVisible_edt_search) {
                            Toast.makeText(MainActivity.this, "333333333333333", Toast.LENGTH_SHORT).show();
                            edtTest.setCursorVisible(true);
                        } else {
                            Toast.makeText(MainActivity.this, "444444444", Toast.LENGTH_SHORT).show();
                            edtTest.setCursorVisible(false);

                            InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v2.getWindowToken(), 0);


                        }
                    }
                    else {

                        Toast.makeText(MainActivity.this, "1111111111111", Toast.LENGTH_SHORT).show();
                        edtTest.setCursorVisible(false);
                        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v2.getWindowToken(), 0);

                        count++;

                    }
                });


            }
        });
    }
}