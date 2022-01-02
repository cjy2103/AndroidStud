package com.example.customviewex1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MyCustomView indicator2 = (MyCustomView) findViewById(R.id.indicator2);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 선택된 별을 가져온다
                int selected = indicator2.getSelected();
                if (selected == 3) {
                    selected = 0; // 맨 오른쪽에 있을때는 처음으로 돌아간다.
                } else {
                    selected++;  // 1개씩 오른쪽으로 이동한다
                }
                Log.d("MainActivity", "selected=" + selected);
                // 다음 선택할 메소드를 실행
                indicator2.setSelected(selected); // 선택 상태를 변경한다.
            }
        });
    }
}