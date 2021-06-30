package com.example.recyclerex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recyclerex2.adapter.TestAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> itemList = new ArrayList<>();

        for(int i=0;i<100;i++){
            if(i<10) {
                itemList.add("0"+String.valueOf(i));
            }
            else if(i>59){
                minute = i/60;
                if((i%60) <10) {
                    itemList.add("0" + String.valueOf(minute) + ": 0"+String.valueOf(i-60*minute));
                }
                else{
                    itemList.add("0" + String.valueOf(minute) + ": "+String.valueOf(i-60*minute));
                }
            }
            else{
                itemList.add(String.valueOf(i));
            }
        }


        // 리사이클러뷰에 LinearLayoutManager 객체 지정.
        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        TestAdapter adapter = new TestAdapter(itemList);
        recyclerView.setAdapter(adapter);

    }
}