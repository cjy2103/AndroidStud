package com.example.immkeyboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.immkeyboard.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        clickOpen();

        clickClose();
    }

    /**
     * @DESC: 초기바인딩
     */
    private void initBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        binding.edtInput.requestFocus();
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    /**
     * @DESC: Imm 키보드열기
     */
    private void clickOpen(){
        binding.btnOpen.setOnClickListener(v->{
            imm.showSoftInput(binding.edtInput,0);
        });
    }

    /**
     * @DESC: Imm 키보드 닫기
     */
    private void clickClose(){
        binding.btnClose.setOnClickListener(v->{
            imm.hideSoftInputFromWindow(binding.edtInput.getWindowToken(),0);
        });
    }



}