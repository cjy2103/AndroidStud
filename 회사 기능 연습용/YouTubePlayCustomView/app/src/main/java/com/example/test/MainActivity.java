package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listView;
    private MyAdapter adapter;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        listView = findViewById(R.id.main_listview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);

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


        adapter = new MyAdapter(itemList, this, onClickItem);
        listView.setAdapter(adapter);

        MyListDecoration decoration = new MyListDecoration();
        listView.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };
}