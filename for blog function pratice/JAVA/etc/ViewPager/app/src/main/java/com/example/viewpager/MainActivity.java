package com.example.viewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.viewpager.adapter.ViewPager2Adapter;
import com.example.viewpager.databinding.ActivityMainBinding;
import com.example.viewpager.model.ListItem;
import com.example.viewpager.model.ListItemModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private ArrayList<ListItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        listAdd();

        viewPagerConnection();

        viewPagerSlide();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        list = new ArrayList<>();
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
     * @DESC: 슬라이드로 이미지 변경 
     */
    private void viewPagerSlide(){
        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0){
                    binding.viewPager2.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

            }
        });
    }
}