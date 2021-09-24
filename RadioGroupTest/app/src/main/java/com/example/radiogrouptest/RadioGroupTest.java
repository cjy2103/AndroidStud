package com.example.radiogrouptest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioGroupTest extends AppCompatActivity {
    private RadioGroup rgGender, rgFoods;
    private RadioButton rbMan, rbWoman, rbChicken, rbPizza;
    private Button btnOk, btnCancel;
    boolean manChecked = false, womanChecked = false , chickenChecked = false, pizzaChecked = false;
    String genderkey = "", foodskey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_group_test);

        rbMan       = findViewById(R.id.rb_man);
        rbWoman     = findViewById(R.id.rb_woman);
        rgFoods     = findViewById(R.id.rg_foods);
        rbChicken   = findViewById(R.id.rb_chiken);
        rbPizza     = findViewById(R.id.rb_pizza);

        rbMan.setChecked(genderUpdate("Man"));
        rbWoman.setChecked(genderUpdate("Woman"));
        rbChicken.setChecked(foodsUpdate("Chicken"));
        rbPizza.setChecked(foodsUpdate("Pizza"));

        initSetting();

        rgGenderChange();

        rgFoodsChange();

        btnOkClick();
        btnCancelClick();




    }

    /**
     * @DESC: 성별 라디오 버튼 저장
     * @param key
     * @param value
     */
    private  void saveGenderRadioButton(String key, boolean value){
        SharedPreferences gender = getSharedPreferences("rgGender",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = gender.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private void saveFoodsRadioButton(String key, boolean value){
        SharedPreferences foods = getSharedPreferences("rgFoods",MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = foods.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * @DESC: 성별 라디오 버튼 업데이트
     * @param key
     * @return
     */
    private boolean genderUpdate(String key){
        SharedPreferences gender = getSharedPreferences("rgGender",MODE_PRIVATE);
        return gender.getBoolean(key, false);
    }

    /**
     * @DESC: 음식 라디오 버튼 업데이트
     * @param key
     * @return
     */
    private boolean foodsUpdate(String key){
        SharedPreferences foods = getSharedPreferences("rgFoods",MODE_PRIVATE);
        return foods.getBoolean(key, false);
    }

    /**
     * @DESC: 최초 실행시 라디오값 세팅
     */
    @SuppressLint("CutPasteId")
    private void initSetting(){
        rbMan       = findViewById(R.id.rb_man);
        rbWoman     = findViewById(R.id.rb_woman);
        rbPizza     = findViewById(R.id.rb_pizza);
        rbChicken   = findViewById(R.id.rb_chiken);

        if(rbMan.isChecked() == false && rbWoman.isChecked() == false) {
            rbMan.setChecked(true);
            saveGenderRadioButton("Man",manChecked);
            saveGenderRadioButton("Woman",womanChecked);
        }
        if(rbChicken.isChecked() == false && rbPizza.isChecked() == false) {
            rbChicken.setChecked(true);
            saveFoodsRadioButton("Chicken",chickenChecked);
            saveFoodsRadioButton("Pizza",pizzaChecked);

        }
    }

    /**
     * @DESC: 성별 라디오 버튼 변경시 일어나는 이벤트
     */
    private void rgGenderChange(){
        rgGender    = findViewById(R.id.rg_gender);
        rbMan       = findViewById(R.id.rb_man);
        rbWoman     = findViewById(R.id.rb_woman);

        rgGender.setOnCheckedChangeListener((rgGender, i) -> {
            if(i == R.id.rb_man){
                manChecked = true;
                womanChecked = false;
                genderkey = "Man";
            } else if(i == R.id.rb_woman){
                manChecked = false;
                womanChecked = true;
                genderkey = "Woman";
            }
        });
    }

    /**
     * @DESC: 음식 라디오 버튼 변경시 일어나는 이벤트
     */
    private void rgFoodsChange(){
        rgFoods     = findViewById(R.id.rg_foods);
        rbChicken   = findViewById(R.id.rb_chiken);
        rbPizza     = findViewById(R.id.rb_pizza);

        rgFoods.setOnCheckedChangeListener((rgFoods, i) ->{
            if(i == R.id.rb_chiken){
                chickenChecked = true;
                pizzaChecked = false;
                foodskey = "Chicken";
            } else if(i == R.id.rb_pizza){
                chickenChecked = false;
                pizzaChecked = true;
                foodskey = "Pizza";
            }
        });
    }

    /**
     * @DESC: OK 버튼 클릭시 발생하는 이벤트
     */
    private void btnOkClick(){

        btnOk = findViewById(R.id.btn_ok);

        btnOk.setOnClickListener(v->{
            if(genderkey.equals("Man")){
                saveGenderRadioButton(genderkey,manChecked);
                saveGenderRadioButton("Woman",womanChecked);
            } else if(genderkey.equals("Woman")){
                saveGenderRadioButton("Man",manChecked);
                saveGenderRadioButton(genderkey,womanChecked);
            }

            if(foodskey.equals("Chicken")){
                saveFoodsRadioButton(foodskey,chickenChecked);
                saveFoodsRadioButton("Pizza",pizzaChecked);
            } else if(foodskey.equals("Pizza")){
                saveFoodsRadioButton("Chicken",chickenChecked);
                saveFoodsRadioButton(foodskey,pizzaChecked);
            }

            Intent intent = new Intent(RadioGroupTest.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    /**
     * @DESC: Cancel 버튼 클릭시 일어나는 이벤트
     */
    private void btnCancelClick(){

        btnCancel = findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(v->{
            Intent intent = new Intent(RadioGroupTest.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}