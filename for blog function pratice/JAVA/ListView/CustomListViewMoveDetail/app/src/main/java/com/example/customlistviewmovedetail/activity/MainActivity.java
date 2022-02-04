package com.example.customlistviewmovedetail.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.example.customlistviewmovedetail.R;
import com.example.customlistviewmovedetail.databinding.ActivityMainBinding;
import com.example.customlistviewmovedetail.listview.ListViewAdapter;
import com.example.customlistviewmovedetail.listview.ListViewItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListViewAdapter adapter;
    private List<Integer> imageList;
    private List<String> titleList;
    private List<String> describeList;
    private List<String> youtubeLinkList;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        addListItem();

        wordFilter();

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
    @SuppressLint("ResourceType")
    private void initialize(){
        mContext = MainActivity.this;
        adapter = new ListViewAdapter();
        imageList = new ArrayList<>(Arrays.asList(R.drawable.baknana,R.drawable.djmax_clear_fail
                ,R.drawable.djmax_falling_in_love, R.drawable.mwama,R.drawable.tamtam));
        titleList = new ArrayList<>(Arrays.asList("박나나","클리어,페일","FallingInLove"
                ,"뫄마","탬탬버린"));
        describeList = new ArrayList<>(Arrays.asList("롤 방송자주하는 트위치 스트리머"
                ,"Djmax 캐릭터","Djmax 곡","트위치 스트리머","트위치 스트리머"));
        youtubeLinkList = new ArrayList<String>(Arrays.asList(getString(R.string.bak_youtube),getString(R.string.djmax_youtube)
                ,getString(R.string.djmax_falling_in_love),getString(R.string.mwama_youtube)
                ,getString(R.string.tamtam_youtube)));
    }

    /**
     * @DESC: 리스트에 아이템 추가
     */
    private void addListItem(){
        binding.listItem.setAdapter(adapter);
        for(int i=0;i<imageList.size();i++){
            adapter.addItem(ContextCompat.getDrawable(mContext,imageList.get(i))
                    ,titleList.get(i),describeList.get(i));
        }
    }

    /**
     * @DESC: 검색
     */
    private void wordFilter(){
        binding.edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String str = binding.edtSearch.getText().toString();
                filterWord(str);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * @DESC: 단어 필터
     */
    private void filterWord(String str){
        adapter.clearItem();
        ArrayList<Integer> positionList = new ArrayList<>();
        int index = 0;
        if(str.length() == 0){
            for(String word : titleList){
                positionList.add(index);
                index++;
            }
        } else {
            for(String word : titleList){
                if(word.toLowerCase().contains(str)){
                    positionList.add(index);
                }
                index++;
            }
        }

        for(int i=0;i<positionList.size();i++){
            adapter.addItem(ContextCompat.getDrawable(mContext,imageList.get(positionList.get(i)))
                    ,titleList.get(positionList.get(i)),describeList.get(positionList.get(i)));
        }

        adapter.notifyDataSetChanged();
    }

    /**
     * @DESC: 아이템 클릭
     */
    private void clickItem(){
        binding.listItem.setOnItemClickListener((adapterView, view, position, id) -> {
            ListViewItem item = (ListViewItem) adapterView.getItemAtPosition(position);

            Intent intent = new Intent(mContext, ListItemDetailActivity.class);
            BitmapDrawable bitmapDrawable = (BitmapDrawable) item.getImage();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            byte[] byteArray = stream.toByteArray();
            Log.v("문제있나?", Arrays.toString(byteArray).length() +"");

            intent.putExtra("image",byteArray);
            intent.putExtra("title",item.getTitle());
            intent.putExtra("describe",item.getDescribe());
            intent.putExtra("youtubeLink",youtubeLinkList.get(position));

            startActivity(intent);
        });
    }
}