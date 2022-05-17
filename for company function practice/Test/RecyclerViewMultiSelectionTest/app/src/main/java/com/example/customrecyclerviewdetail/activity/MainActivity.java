package com.example.customrecyclerviewdetail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.customrecyclerviewdetail.R;
import com.example.customrecyclerviewdetail.databinding.ActivityMainBinding;
import com.example.customrecyclerviewdetail.adapter.CustomRecyclerAdatper;
import com.example.customrecyclerviewdetail.model.ListItemModel;
import com.example.customrecyclerviewdetail.model.MyListItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayList<MyListItem> myListItems;
    private CustomRecyclerAdatper adapter;
    private ArrayList<Integer> searchIndexList;

    //삭제 리스트
    private boolean isEnable = false;
    private final ArrayList<Integer> itemSelectedList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        listadd();

        recyclerViewConnection();

        wordInput();

        itemClick();

        testLong();

        deleteItem();
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
        myListItems = new ArrayList<>();
        searchIndexList = new ArrayList<>();
        binding.recyclerList.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * @DESC: 리스트 추가
     */
    private void listadd(){
        String imageUri = "drawable://";
        addItem(getResources().getString(R.string.baknana),getResources().getString(R.string.bak_describe)
                ,imageUri + R.drawable.baknana, getResources().getString(R.string.baknana_link));
        addItem(getResources().getString(R.string.djmax),getResources().getString(R.string.djmax_describe)
                ,imageUri + R.drawable.djmax_clear_fail, getResources().getString(R.string.djmax_archive));
        addItem(getResources().getString(R.string.djmax_falling_love), getResources().getString(R.string.djmax_falling_love_describe)
                ,imageUri + R.drawable.djmax_falling_in_love, getResources().getString(R.string.djmax_falling_love_link));
        addItem(getResources().getString(R.string.mwamwa), getResources().getString(R.string.mwamwa_describe)
                ,imageUri + R.drawable.mwama, getResources().getString(R.string.mwamwa_link));
        addItem(getResources().getString(R.string.tamtam), getResources().getString(R.string.mwamwa_describe)
                ,imageUri + R.drawable.tamtam, getResources().getString(R.string.tamtam_link));
    }

    /**
     * @DESC: 아이템 추가
     * @param title
     * @param describe
     * @param path
     */
    private void addItem(String title, String describe, String path, String link){
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setTitle(title);
        listItemModel.setDescribe(describe);
        listItemModel.setUri(path);
        listItemModel.setChannelLink(link);
        listItemModel.setSelected(false);

        MyListItem myListItem = new MyListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        myListItem.setList(items);

        myListItems.add(myListItem);
    }

    /**
     * @DESC: RecyclerView에 어댑터 연결
     */
    private void recyclerViewConnection(){
        adapter = new CustomRecyclerAdatper(this,this,myListItems,myListItems.size(),binding.edtInput.getText().toString());
        binding.recyclerList.setAdapter(adapter);
    }

    /**
     * @DESC: 단어 입력 감지 함수
     */
    private void wordInput(){
        binding.edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = binding.edtInput.getText().toString().toLowerCase();
                filterWord(str);
                testLong();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * @DESC: 검색어 필터
     * @param word
     */
    private void filterWord(String word){
        searchIndexList.clear();

        if(binding.edtInput.getText().toString().equals("")){
            adapter = new CustomRecyclerAdatper(this, this, myListItems,myListItems.size(),word);

            binding.recyclerList.setAdapter(adapter);
            itemClick();

        } else {
            for(int i=0;i<myListItems.size();i++){
                if(myListItems.get(i).getList().get(0).getTitle().toLowerCase().contains(word)){
                    searchIndexList.add(i);
                }
            }

            adapter = new CustomRecyclerAdatper(this, this, myListItems,searchIndexList,
                    searchIndexList.size(),word);

            binding.recyclerList.setAdapter(adapter);
            filterWordClick();
        }

    }

    /**
     * @DESC: Recycler 아이템 클릭 case 빈칸
     */
    private void itemClick(){

        adapter.setOnItemClickListener((v, position) -> {
            if(!isEnable) {
                Log.v("1111111","");
                Intent intent = new Intent(this, RecyclerItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemObject", myListItems.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
            }
            testLong();
        });
    }

    /**
     * @DESC: Recycler 아이템 클릭 case 단어입력
     */
    private void filterWordClick(){
        adapter.setOnItemClickListener((v, position) -> {
            if(!isEnable) {
                Log.v("2222222","");
                Intent intent = new Intent(this, RecyclerItemDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("itemObject", myListItems.get(searchIndexList.get(position)));
                intent.putExtras(bundle);

//            intent.putExtra("imagePath",myListItems.get(searchIndexList.get(position)).getList().get(0).getUri());
//            intent.putExtra("title",myListItems.get(searchIndexList.get(position)).getList().get(0).getTitle());
//            intent.putExtra("describe",myListItems.get(searchIndexList.get(position)).getList().get(0).getDescribe());
//            intent.putExtra("youtubeLink",myListItems.get(searchIndexList.get(position)).getList().get(0).getChannelLink());
                startActivity(intent);
            }
            testLong();
        });


    }

    @SuppressLint("NotifyDataSetChanged")
    private void testLong(){
        adapter.setLongItemClickListener((v,position) ->{
            selectItem(position);
            adapter = new CustomRecyclerAdatper(this,this,myListItems,myListItems.size(),binding.edtInput.getText().toString());
            binding.recyclerList.setAdapter(adapter);
            itemClick();
            testLong();
        });


        adapter.setDeleteItemListener((v, position) -> {
            Log.v("지금 값뭔데?",""+isEnable);
                if (itemSelectedList.contains(position)) {
                    itemSelectedList.remove((Integer) position);
                    myListItems.get(position).getList().get(0).setSelected(false);
                    if (itemSelectedList.isEmpty()) {
                        binding.consDeleteBar.setVisibility(View.GONE);
                        isEnable = false;
                    }
                    adapter = new CustomRecyclerAdatper(this, this, myListItems, myListItems.size(), binding.edtInput.getText().toString());
                    binding.recyclerList.setAdapter(adapter);
                    itemClick();
                    testLong();
                } else if (isEnable) {
                    selectItem(position);
                    adapter = new CustomRecyclerAdatper(this, this, myListItems, myListItems.size(), binding.edtInput.getText().toString());
                    binding.recyclerList.setAdapter(adapter);
                    itemClick();
                    testLong();
                }
        });

    }

    private void selectItem(int position){
        isEnable = true;
        itemSelectedList.add(position);
        myListItems.get(position).getList().get(0).setSelected(true);
        binding.consDeleteBar.setVisibility(View.VISIBLE);
    }

    private void deleteItem(){
        binding.consDeleteBar.setOnClickListener(v->{
            if(!itemSelectedList.isEmpty()){
                Toast.makeText(this, "삭제하면될듯?", Toast.LENGTH_SHORT).show();
                isEnable = false;
                binding.consDeleteBar.setVisibility(View.GONE);
            }
        });
    }


}