package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.roomtest.DB.USER;
import com.example.roomtest.DB.UserDB;
import com.example.roomtest.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserDB userDB = null;
    private USER user;
    private String str = "";
    private int currentUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();

        initialize();

        insertData();

        checkData();

        deleteData();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        userDB = UserDB.getInstance(this);
        user = new USER();
        currentUid = 2;
    }

    private void insertData(){
        binding.btnInsert.setOnClickListener(v->{
            user.setUid(currentUid);
            user.setName("Test");
            user.setAge("22");
            user.setSex("남");


            userDB.userDao().insert(user).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

    }

    @SuppressLint("CheckResult")
    private void checkData(){
        binding.btnSelect.setOnClickListener(v->{
            userDB.userDao().loadById(currentUid).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(item -> {

                        for (USER user : item) {
                            str += user.toString();
                        }
                        Log.v("값이 있나...",str);
                        str = "";
                    });
        });


    }

    private void deleteData(){
        binding.btnDelete.setOnClickListener(v->{
            userDB.userDao().delete().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
        });

    }


}