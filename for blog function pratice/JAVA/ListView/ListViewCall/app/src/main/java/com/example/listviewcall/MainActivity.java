package com.example.listviewcall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.listviewcall.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<String> list;
    private Context mContext;
    private ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();

        initialize();
        
        listAdd();

        listConnection();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        mContext    = MainActivity.this;
        list        = new ArrayList<>();
    }

    /**
     * @DESC: 리스트 아이템 추가
     */
    private void listAdd(){
        list.add("000-0000-0000");
        list.add("111-1111-1111");
        list.add("222-2222-2222");
        list.add("333-3333-3333");
        list.add("444-4444-4444");
        list.add("555-5555-5555");
        list.add("666-6666-6666");
        list.add("777-7777-7777");
        list.add("888-8888-8888");
        list.add("999-9999-9999");
    }

    /**
     * @DESC: List 어댑터에 연결
     */
    private void listConnection(){
        adapter = new ArrayAdapter<>(mContext,android.R.layout.simple_list_item_1,list);
        binding.listItem.setAdapter(adapter);
    }
}