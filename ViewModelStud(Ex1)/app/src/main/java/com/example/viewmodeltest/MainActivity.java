package com.example.viewmodeltest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edtInput;
    private TextView tvData;
    private Button btnInput;

    EditViewModel viewModel;

    /*******************************************************************************
     ********************************* 생명주기 *************************************
     *******************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBinding();

        initialize();

        inputData();

    }


    /*******************************************************************************
     ******************************* 이벤트 함수 *************************************
     *******************************************************************************/

    /**
     * @DESC: 데이터 입력
     */
    private void inputData(){
        btnInput.setOnClickListener(v->{
            String inputData = edtInput.getText().toString();
            viewModel.getCurretEdit().setValue(inputData);
        });
    }

    /*******************************************************************************
     ******************************* 사용자 함수 *************************************
     *******************************************************************************/
    /**
     * @DESC: 초기 바인딩
     */
    private void initBinding(){
        edtInput = findViewById(R.id.edt_input);
        tvData   = findViewById(R.id.tv_data);
        btnInput = findViewById(R.id.btn_input);
    }

    /**
     * @DESC: 초기화
     */
    private void initialize(){
        viewModel = new ViewModelProvider(this,new EditViewModelFactory()).get(EditViewModel.class);

        Observer<String> dataObserver = newData -> tvData.setText(newData);

        viewModel.getCurretEdit().observe(this, dataObserver);
    }
    
    
}