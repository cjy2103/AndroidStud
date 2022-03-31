package com.example.bottomnavigation.activity.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bottomnavigation.R;
import com.example.bottomnavigation.databinding.ActivityMainBinding;
import com.example.bottomnavigation.fragment.DjMaxFragment;
import com.example.bottomnavigation.fragment.MidoriFragment;
import com.example.bottomnavigation.fragment.MomoiFragment;
import com.example.bottomnavigation.util.SystemUtil;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Fragment djMaxFragment, momoiFramgnet, midoriFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        initialize();

        bottomNavigationSelect();
    }

    /**
     * @DESC: 뷰바인딩
     */
    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        binding.bottomNav.setItemIconTintList(null);

        SystemUtil systemUtil = new SystemUtil();
        systemUtil.sofNavigationBarHide(getWindow());
        systemUtil.statusbarSetting(getWindow(),this,binding.mainConstraint);

        djMaxFragment = new DjMaxFragment();
        // 첫화면
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, djMaxFragment).commit();

    }

    /**
     * @DESC: 바텀 네비바 메뉴 선택
     */
    private void bottomNavigationSelect(){
        binding.bottomNav.setOnItemSelectedListener(item->{
            changeFramgnet(item);
            return true;
        });
    }

    @SuppressLint("NonConstantResourceId")
    public void changeFramgnet(MenuItem item){
        switch (item.getItemId()){
            case R.id.djmax_fragment:
                if(djMaxFragment == null){
                    djMaxFragment = new DjMaxFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, djMaxFragment).commit();
                break;
            case R.id.momoi_fragment:
                if(momoiFramgnet == null){
                    momoiFramgnet = new MomoiFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, momoiFramgnet).commit();
                break;
            case R.id.midori_fragment:
                if(midoriFragment == null){
                    midoriFragment = new MidoriFragment();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, midoriFragment).commit();
                break;
            default:
                break;
        }
    }


}