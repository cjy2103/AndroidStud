package com.example.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.databinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding();

        clickBtn();
    }

    /**
     * @DESC: dataBinding
     */
    private void dataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    /**
     * @DESC: 버튼 클릭
     */
    private void clickBtn(){
        binding.btnClick.setOnClickListener(v->{
            Toast.makeText(this, "버튼 클릭됨",Toast.LENGTH_LONG).show();
        });
    }

    

}