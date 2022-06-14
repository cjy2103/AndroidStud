package com.example.alertdialogex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.example.alertdialogex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initBinding();

        initialize();

        openAlertDialog();
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
        mContext = MainActivity.this;
    }

    /**
     * @DESC: AlertDialog 열기
     */
    private void openAlertDialog(){
//        binding.btnOpen.setOnClickListener(v->{
//            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
//
//            builder.setTitle("AlertDialog 띄우기").
//                    setMessage("이부분은 메시지를 입력하는 부분입니다.")
//                    .setPositiveButton("OK", (dialog, i) -> {
//                        Toast.makeText(mContext,"Ok버튼 클릭함",Toast.LENGTH_SHORT).show();
//                    })
//                    .setNegativeButton("Cancel",(dialog, i) ->{
//                        Toast.makeText(mContext,"Cancel버튼 클릭함",Toast.LENGTH_SHORT).show();
//                    }).create().show();
//                }
//        );

        Button btnTest = findViewById(R.id.btn_open);
        btnTest.setOnClickListener(v->{
            Log.v("test","test");
        });
    }
}