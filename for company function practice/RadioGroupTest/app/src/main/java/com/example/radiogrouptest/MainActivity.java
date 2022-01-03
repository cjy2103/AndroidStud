package com.example.radiogrouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnMove;
    TextView txtChecked;
    boolean genderCheck = false , foodsCheck = false;

    private int genderCount = 0, foodsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMoveClick();
        
        txtSetting();


    }

    /**
     * @DESC: 테스트 버튼 클릭
     */
    private void btnMoveClick(){
        btnMove = findViewById(R.id.btn_move);

        btnMove.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, RadioGroupTest.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * @DESC: 저장한 성별값 가져오기
     * @param key
     * @return
     */
    private boolean getGender(String key){
        SharedPreferences gender = getSharedPreferences("rgGender",MODE_PRIVATE);
        return gender.getBoolean(key,false);
    }

    private boolean getFoods(String key){
        SharedPreferences foods = getSharedPreferences("rgFoods",MODE_PRIVATE);
        return foods.getBoolean(key,false);
    }

    /**
     * @DESC: 현제 체크된 라디오는 어떤것들인가?
     */
    private void txtSetting(){

        String str = "", genderStr = "", foodsStr = "";

        txtChecked = findViewById(R.id.txt_checked);

        genderCheck = getGender("Man");
        if(genderCheck){
            genderStr+= "남자 체크됨";
            genderCount++;
        }
        genderCheck = getGender("Woman");
        if(genderCheck){
            genderStr+= "여자 체크됨";
            genderCount++;
        }

        foodsCheck = getFoods("Chicken");
        if(foodsCheck){
            foodsStr+= ", 치킨 체크됨";
            foodsCount++;
        }

        foodsCheck = getFoods("Pizza");
        if(foodsCheck){
            foodsStr+= ", 피자 체크됨";
            foodsCount++;
        }
        
        // 최초 실행 일때 라디오 값이 적용되지 않는 문제 때문에 코딩
        if(genderCount == 0 && foodsCount ==0){
            str = "남자 체크됨, 치킨 체크됨";
        }else if(genderCount == 0 && foodsCount == 1){
            str = "남자체크됨" + foodsStr;
        }else if(genderCount == 1 && foodsCount == 0){
            str = genderStr + ", 치킨 체크됨";
        } else {
            str = genderStr + foodsStr;
        }

        txtChecked.setText(str);

    }

}