package com.example.edittextex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.edittextex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initBinding();

        initialize();

        sendMessage();


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
     * @DESC; EditText 값 전달
     */
    private void sendMessage(){
        binding.edtSend.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            View view = this.getCurrentFocus();
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                binding.tvMessage.setText(binding.edtSend.getText().toString());
                binding.edtSend.clearFocus();
                // 키보드 hide 해주는 부분
                if(view != null){
                    InputMethodManager imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(),0);
                }
                return true;
            }
            return false;
        });
    }
}