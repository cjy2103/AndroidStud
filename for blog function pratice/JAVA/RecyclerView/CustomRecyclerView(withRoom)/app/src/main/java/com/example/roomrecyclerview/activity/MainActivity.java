package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.roomrecyclerview.activity.adapter.MainRecyclerItemAdapter;
import com.example.roomrecyclerview.model.ListItemModel;
import com.example.roomrecyclerview.model.MyListItem;
import com.example.roomrecyclerview.databinding.ActivityMainBinding;
import com.example.roomrecyclerview.room.Data;
import com.example.roomrecyclerview.room.RoomDB;
import com.example.roomrecyclerview.util.SystemUtil;

import java.util.ArrayList;
import java.util.Collections;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SystemUtil systemUtil;

    private RoomDB roomDB;

    public static Context context;

    private boolean refresh = false;

    private ArrayList<MyListItem> myListItems;
    private MainRecyclerItemAdapter adapter;

    private ArrayList<Integer> searchIndexList;

    /*********************************************************************************************
     ************************************* 라이프 사이클 *******************************************
     *********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickInsert();

        dataLoad();

        wordInput();

    }

    @Override
    protected void onResume() {
        super.onResume();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this, binding.consMain);

        if(refresh){
            myListItems.clear();
            dataLoad();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        refresh = false;
    }

    /*********************************************************************************************
     ************************************** 이벤트 함수 ********************************************
     *********************************************************************************************/

    private void clickInsert(){
        binding.consInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }

    private void wordInput(){
        binding.edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = binding.edtInput.getText().toString();
                filterWord(str);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void filterWord(String word){
        searchIndexList.clear();

        if(binding.edtInput.getText().toString().equals("")){
            adapter = new MainRecyclerItemAdapter(this, this, myListItems, myListItems.size(), "");
            binding.recyclerView.setAdapter(adapter);
            itemClick();
        } else {
            for(int i=0;i<myListItems.size();i++){
                if(myListItems.get(i).getList().get(0).getTitle().toLowerCase().contains(word.toLowerCase())){
                    searchIndexList.add(i);
                }
            }

            adapter = new MainRecyclerItemAdapter(this, this, myListItems, searchIndexList.size(), word, searchIndexList);
            binding.recyclerView.setAdapter(adapter);
            filterWordClick();
        }
    }

    /**
     * @DESC: Recycler 아이템 클릭 case 빈칸
     */
    private void itemClick(){
        adapter.setOnItemClickListener((v,position) ->{
            Intent intent = new Intent(this, RecyclerItemDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("itemObject",myListItems.get(position));
            intent.putExtras(bundle);
            startActivity(intent);

        });
    }

    /**
     * @DESC: Recycler 아이템 클릭 case 단어입력
     */
    private void filterWordClick() {
        adapter.setOnItemClickListener((v, position) -> {
            Intent intent = new Intent(this, RecyclerItemDetailActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("itemObject", myListItems.get(searchIndexList.get(position)));
            intent.putExtras(bundle);

            startActivity(intent);
        });
    }

    /*********************************************************************************************
     **************************************** 일반 함수 ********************************************
     *********************************************************************************************/

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        systemUtil = new SystemUtil();

        myListItems = new ArrayList<>();

        context = this;

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        roomDB = RoomDB.getInstance(this);

        searchIndexList = new ArrayList<>();

    }

    @SuppressLint("CheckResult")
    private void dataLoad(){
        roomDB.dataDao().getAll().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( item ->{
                    for(Data data : item) {
                        dataListAdd(data);
                    }
                    recyclerConnection();
                });
    }

    private void dataListAdd(Data data){
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setTitle(data.getTitle());
        listItemModel.setDescribe(data.getDescribe());
        listItemModel.setChannelLink(data.getYoutubeLink());
        listItemModel.setUri(data.getImagePath());
        listItemModel.setImageCase(data.getImageCase());

        MyListItem myListItem = new MyListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        myListItem.setList(items);
        myListItems.add(myListItem);
    }

    private void recyclerConnection(){
        Collections.reverse(myListItems);
        String str = binding.edtInput.getText().toString();
        filterWord(str);
    }



    public void mainRecyclerRefresh(){
        refresh = true;
    }



}