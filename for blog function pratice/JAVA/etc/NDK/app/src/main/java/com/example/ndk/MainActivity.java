package com.example.ndk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.ndk.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    static {
        try{
            System.loadLibrary("test");
        }
        catch (UnsatisfiedLinkError ule){
            Log.v("NDKTest","NDK 오류발생");
            ule.printStackTrace();
        }
    }

    private ActivityMainBinding binding;
    private native String getNdk();

    private String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewBinding();
        initialize();
        clickNdkTest();
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
        str = getNdk();
    }

    /**
     * @DESC: NDK 테스트 버튼 클릭
     */
    private void clickNdkTest(){
        binding.btnClick.setOnClickListener(v->{
            binding.tvNdk.setText(str);
        });
    }
}