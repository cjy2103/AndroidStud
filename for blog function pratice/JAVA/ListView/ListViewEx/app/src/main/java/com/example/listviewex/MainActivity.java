package com.example.listviewex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.listviewex.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    List<String> list;
    private Context mContext;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();

        initialize();
        
        listAdd();

        listConnection();

        clickItem();
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
        mContext = MainActivity.this;
        list = new ArrayList<>();
    }

    /**
     * @DESC: 리스트 뷰 아이템 추가
     */
    private void listAdd(){
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("수박");
        list.add("망고");
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("수박");
        list.add("망고");
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("수박");
        list.add("망고");
    }

    /**
     * @DESC: List 어댑터에 연결
     */
    private void listConnection(){
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, list);
        binding.listItem.setAdapter(adapter);
    }

    /**
     * @DESC: 아이템 클릭
     */
    private void clickItem(){
        binding.listItem.setOnItemClickListener((adapterView, view, position, l) -> {
            binding.tvSelect.setText((String) adapterView.getItemAtPosition(position));
        });
    }
}