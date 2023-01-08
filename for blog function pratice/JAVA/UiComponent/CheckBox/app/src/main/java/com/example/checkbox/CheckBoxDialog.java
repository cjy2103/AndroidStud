package com.example.checkbox;

import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.checkbox.databinding.CustomDialogBinding;
import com.example.checkbox.util.LogUtil;

import java.util.Objects;

public class CheckBoxDialog extends DialogFragment {

    private CustomDialogBinding binding;
    private int limit = 1;
    private int selectCount = 0;
    private String str = "";
    private String selectWord = "";
    private Context context;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = CustomDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickOk();
        clickCancel();
        clickClose();
        ckApple();
        ckBanana();
        ckOrange();
    }

    private void ckApple(){
        binding.ckApple.setOnClickListener(v->{
            check(binding.ckApple,"사과");
        });
    }

    private void ckBanana(){
        binding.ckBanana.setOnClickListener(v->{
            check(binding.ckBanana,"바나나");
        });
    }

    private void ckOrange(){
        binding.ckOrange.setOnClickListener(v->{
            check(binding.ckBanana,"오렌지");
        });
    }

    private void check(CheckBox ck, String word){
        if(selectCount == 0){
            if(ck.isChecked()){
                str = word;
                selectCount++;
                selectWord = word;
            } else if(!ck.isChecked() && word.equals(selectWord)){
                selectCount--;
            }
        } else {
            Toast.makeText(context, "1개 이상의 값은 선택할수 없습니다.", Toast.LENGTH_SHORT).show();
            ck.setChecked(false);
        }
    }

    private void clickOk(){
        binding.tvOk.setOnClickListener(v->{
            if(binding.ckApple.isChecked()){
                str = str + "사과";
            }
            if(binding.ckBanana.isChecked()){
                str = str + "바나나";
            }
            if(binding.ckOrange.isChecked()){
                str = str + "오렌지";
            }
            dismiss();
        });
    }
    private void clickCancel(){
        binding.tvCancel.setOnClickListener(v->{
            dismiss();
        });
    }

    private void clickClose(){
        binding.ibClose.setOnClickListener(v->{
            dismiss();
        });
    }



}
