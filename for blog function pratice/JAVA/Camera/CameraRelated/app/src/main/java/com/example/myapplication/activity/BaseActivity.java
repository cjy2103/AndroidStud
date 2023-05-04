package com.example.myapplication.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;

import com.example.myapplication.util.SystemUtil;

import java.lang.reflect.Method;
import java.util.function.Function;

public class BaseActivity<B extends ViewBinding> extends AppCompatActivity {

    private Function<LayoutInflater, B> bindingFactory;
    public B binding;
    private SystemUtil systemUtil;

    public BaseActivity(Function<LayoutInflater, B> bindingFactory) {
        this.bindingFactory = bindingFactory;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        systemUtil = new SystemUtil();
        binding = bindingFactory.apply(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    public void hideStatusBar(ConstraintLayout statusBarBackground){
        systemUtil.statusbarSetting(getWindow(), this, statusBarBackground);
    }

    public void hideBottomBar(){
        systemUtil.sofNavigationBarHide(getWindow());
    }
}
