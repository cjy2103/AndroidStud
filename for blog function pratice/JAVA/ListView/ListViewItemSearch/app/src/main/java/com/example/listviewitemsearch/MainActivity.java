package com.example.listviewitemsearch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;

import com.example.listviewitemsearch.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<String> list;
    private ArrayList<String> arrayList;
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
        
        wordInput();
    }

    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC; 초기화
     */
    private void initialize(){
        mContext    = MainActivity.this;
        list        = new ArrayList<>();
        arrayList   = new ArrayList<>();
    }

    /**
     * @DESC: 리스트 목록 추가
     */
    private void listAdd(){
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("망고");
        list.add("수박");
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("망고");
        list.add("수박");
        list.add("사과");
        list.add("바나나");
        list.add("키위");
        list.add("망고");
        list.add("수박");
    }

    /**
     * @DESC: 리스트 연결
     */
    private void listConnection(){
        arrayList.addAll(list);
        adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1,list);
        binding.listItem.setAdapter(adapter);
    }

    /**
     * @DESC: 단어 입력
     */
    private void wordInput(){
        binding.edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                String str = binding.edtInput.getText().toString();
                filterWord(str);

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * @DESC: 리스트 필터
     * @param str
     */
    private void filterWord(String str){
        list.clear();

        if(str.length() == 0){
            list.addAll(arrayList);
        } else {
            for(String word : arrayList){
                if(word.contains(str)){
                    list.add(word);
                }
            }
            adapter.notifyDataSetChanged();
        }

    }
}