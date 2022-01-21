package com.example.customdialog.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.customdialog.MainActivity;
import com.example.customdialog.R;
import com.example.customdialog.databinding.CustomDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class CustomDialog extends DialogFragment {

    private CustomDialogBinding binding;
    private Context mContext;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return initBinding(inflater,container);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFontSetting();

        dialogSetting();

        clickClose();

        clickCancel();

        clickOk();

    }


    /**
     * @DESC: 초기 바인딩
     * @param inflater
     * @param container
     */
    private View initBinding(@NotNull LayoutInflater inflater, @org.jetbrains.annotations.Nullable ViewGroup container){
        binding = CustomDialogBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    /**
     * @DESC: 폰트세팅
     */
    private void initFontSetting(){
        Typeface tfMapleBold    = Typeface.createFromAsset(mContext.getAssets(), "Maplestory Bold.ttf");
        Typeface tfMapleLight   = Typeface.createFromAsset(mContext.getAssets(), "Maplestory Light.ttf");

        binding.tvTitle.setTypeface(tfMapleBold);
        binding.tvMessage.setTypeface(tfMapleLight);
        binding.tvCancel.setTypeface(tfMapleLight);
        binding.tvOk.setTypeface(tfMapleLight);

    }

    /**
     * @DESC: 다이얼로그 옵션 세팅
     */
    private void dialogSetting(){
        setCancelable(false); // 외부영역을 터치해도 종료되지 않게 함
        if(Objects.requireNonNull(getDialog()).getWindow() != null){
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().setDimAmount(0.2F);  // 뒷 배경 20% dim 처리

//            getDialog().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND); // 뒷배경 색 아예 없애기
        }
    }

    /**
     * @DESC: X버튼 클릭
     */
    private void clickClose(){
        binding.ibClose.setOnClickListener(v->{
            dismiss();
        });
    }

    /**
     * @DESC: Cancel 클릭
     */
    private void clickCancel(){
        binding.tvCancel.setOnClickListener(v->{
            dismiss();
        });
    }

    /**
     * @DESC: OK 버튼 클릭
     */
    private void clickOk(){
        binding.tvOk.setOnClickListener(v->{
            dismiss();
            ((MainActivity)mContext).finish();
        });
    }
}
