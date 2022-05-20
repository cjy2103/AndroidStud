package com.example.roomdbtest.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.roomdbtest.databinding.ActivityInsertBinding;
import com.example.roomdbtest.room.Data;
import com.example.roomdbtest.room.RoomDB;
import com.example.roomdbtest.util.LogUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class InsertActivity extends AppCompatActivity {

    private ActivityInsertBinding binding;

    private RoomDB roomDB = null;
    private Data data;


    /**************************************************************************
     ********************************* 생명주기 ********************************
     *************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewBinding();

        initialize();

        btnOk();

        btnCancel();
    }

    /**************************************************************************
     ****************************** 이벤트 함수 ********************************
     *************************************************************************/

    private void btnOk(){
        binding.btnOk.setOnClickListener(v->{

            if(binding.edtTitle.getText().toString().isEmpty()){
                Toast.makeText(this, "Title은 비어있으면 안됩니다.", Toast.LENGTH_SHORT).show();
            }

            dbInsert();

        });
    }

    private void btnCancel(){
        binding.btnCancel.setOnClickListener(v->{
            finish();
        });
    }

    /**************************************************************************
     ****************************** 사용자 함수 ********************************
     *************************************************************************/

    private void viewBinding(){
        binding = ActivityInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        roomDB = RoomDB.getInstance(this);
        data = new Data();
    }

    @SuppressLint("CheckResult")
    private void dbInsert(){
        data.setTitle(binding.edtTitle.getText().toString());
        data.setMsg(binding.edtMsg.getText().toString());

        roomDB.dataDao().insert(data).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->{
                    Toast.makeText(this, "데이터 추가 완료", Toast.LENGTH_SHORT).show();
                    finish();
                });
    }
}