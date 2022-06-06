package com.example.roomrecyclerview.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.adapter.MainRecyclerItemAdapter;
import com.example.roomrecyclerview.activity.model.ListItemModel;
import com.example.roomrecyclerview.activity.model.MyListItem;
import com.example.roomrecyclerview.databinding.ActivityMainBinding;
import com.example.roomrecyclerview.room.Data;
import com.example.roomrecyclerview.room.RoomDB;
import com.example.roomrecyclerview.util.LogUtil;
import com.example.roomrecyclerview.util.SystemUtil;

import java.util.ArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        clickInsert();

        dataLoad();

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

    private void clickInsert(){
        binding.consInsert.setOnClickListener(v->{
            Intent intent = new Intent(this, InsertActivity.class);
            startActivity(intent);
        });
    }


    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        systemUtil = new SystemUtil();

        myListItems = new ArrayList<>();

        Typeface tfMapleLight = getResources().getFont(R.font.font_english);
        binding.tvTitle.setTypeface(tfMapleLight);

        context = this;

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        roomDB = RoomDB.getInstance(this);

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

        LogUtil.log("data값:"+ data.getDescribe());

        MyListItem myListItem = new MyListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        myListItem.setList(items);
        myListItems.add(myListItem);
    }

    private void recyclerConnection(){
        adapter = new MainRecyclerItemAdapter(this, this , myListItems);
        binding.recyclerView.setAdapter(adapter);
    }



    public void mainRecyclerRefresh(){
        refresh = true;
    }


}