package com.example.checkbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CustomDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialogSetting();
        clickEvent();

    }

    private void dialogSetting(){
        setCancelable(false);
        if(Objects.requireNonNull(getDialog()).getWindow() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().setDimAmount(0.2f);
        }
    }

    private void clickEvent(){
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
            check(binding.ckOrange,"오렌지");
        });
    }

    @SuppressLint("SetTextI18n")
    private void check(CheckBox ck, String word){
        if(selectCount == 0){
            if(ck.isChecked()){
                str = word;
                selectCount++;
                selectWord = word;
                binding.tvMessage.setText("선택된것 :"+word);

            }
        } else if(!ck.isChecked() && word.equals(selectWord)){
            selectCount = 0;
            binding.tvMessage.setText("선택된 아이템?");
        } else {
            Toast.makeText(context, "1개 이상의 값은 선택할수 없습니다.", Toast.LENGTH_SHORT).show();
            ck.setChecked(false);
        }
    }

    private void clickOk(){
        binding.tvOk.setOnClickListener(v->{
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
