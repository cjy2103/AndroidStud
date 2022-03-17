package com.example.wifimobilecheck;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.wifimobilecheck.databinding.ActivityMainBinding;
import com.example.wifimobilecheck.network.NetWorkStatus;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        stateCheck();
    }

    /**
     * @DESC: 뷰바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 네트워크 상태체크
     */
    @SuppressLint("SetTextI18n")
    private void stateCheck(){
        binding.btnState.setOnClickListener(v->{
            int netWorkState = NetWorkStatus.getConnectStatus(this);
            if(netWorkState == NetWorkStatus.TYPE_WIFI){
                binding.tvState.setText("상태 : WIFI");
            } else if(netWorkState == NetWorkStatus.TYPE_MOBILE){
                binding.tvState.setText("상태 : MOBILE");
            } else {
                binding.tvState.setText("상태 : 끊김");
            }
        });
    }

}