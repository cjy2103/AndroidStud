package com.example.buttonclickevent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvWord;
    Button btnWordChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        wordChange();


    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        tvWord = findViewById(R.id.tv_word);
        btnWordChange = findViewById(R.id.btn_word_change);
    }

    /**
     * @DESC: 텍스트 변경 이벤트
     */
    private void wordChange(){
        btnWordChange.setOnClickListener(v->{
            tvWord.setText("단어 변경");
        });
    }


}