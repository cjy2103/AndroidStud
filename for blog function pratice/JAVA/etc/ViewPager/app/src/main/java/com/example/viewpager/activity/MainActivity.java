package com.example.viewpager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.Toast;

import com.example.viewpager.R;
import com.example.viewpager.adapter.ViewPager2Adapter;
import com.example.viewpager.databinding.ActivityMainBinding;
import com.example.viewpager.model.ListItem;
import com.example.viewpager.model.ListItemModel;
import com.example.viewpager.util.SystemUtil;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<ListItem> list;

    private ArrayList<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        listAdd();

        viewPagerConnection();

//        viewPagerSlideEvent();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        SystemUtil systemUtil = new SystemUtil();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this,binding.mainRootLayout);
        list = new ArrayList<>();
        nameList = new ArrayList<>(Arrays.asList("박나나","클리어&페일","Falling in love","뫄뫄","탬탬"));
    }

    private void listAdd(){
        String imageUri = "drawable://";
        addItem(imageUri + R.drawable.baknana);
        addItem(imageUri + R.drawable.djmax_clear_fail);
        addItem(imageUri + R.drawable.djmax_falling_in_love);
        addItem(imageUri + R.drawable.mwama);
        addItem(imageUri + R.drawable.tamtam);
    }

    private void addItem(String imagePath) {
        ListItemModel listItemModel = new ListItemModel();
        listItemModel.setImagePath(imagePath);

        ListItem listItem = new ListItem();

        ArrayList<ListItemModel> items = new ArrayList<>();

        items.add(listItemModel);

        listItem.setList(items);

        list.add(listItem);
    }

    private void viewPagerConnection(){
        binding.viewPager2.setAdapter(new ViewPager2Adapter(this, this, list));
        new TabLayoutMediator(binding.tabLayout, binding.viewPager2, (tab, position) -> {

        }).attach();

    }

    /**
     * @DESC: 슬라이드 이벤트 발생
     */
    private void viewPagerSlideEvent(){
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                showToast(nameList.get(position));
            }
        });
    }

    private void showToast(String msg){
        Toast.makeText(this, msg+" 선택됨", Toast.LENGTH_SHORT).show();
    }
}