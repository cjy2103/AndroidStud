package com.example.roomrecyclerview.activity.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.InsertActivity;
import com.example.roomrecyclerview.activity.LocalImageSelectActivity;
import com.example.roomrecyclerview.databinding.DialogImageSelectBinding;
import com.example.roomrecyclerview.util.LogUtil;

public class ImageSelectDialog extends DialogFragment {

    private DialogImageSelectBinding binding;
    private Context mContext;
    private Activity mActivity;
    private InsertActivity insertActivity;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
        mActivity = (Activity) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
        mActivity = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        insertActivity.albumSelectCallback(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinding(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = mActivity.getSharedPreferences("image",Context.MODE_PRIVATE);
        boolean itemClick = sharedPreferences.getBoolean("imageSelect",false);
        if(itemClick){
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("imageSelect",false);
            editor.apply();
            dismiss();
        }
    }

    private View viewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DialogImageSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        dataLoad();

        clickClose();

        clickLocal();


        insertActivity = (InsertActivity)mActivity;

    }

    private void dataLoad(){
        assert getArguments() != null;
        String test = getArguments().getString("Test");
        LogUtil.log("값있나?"+test);
    }

    private void clickClose(){
        binding.ibClose.setOnClickListener(v->{
            dismiss();
        });
    }


    private void clickLocal(){
        binding.btnLocal.setOnClickListener(v->{
            Intent intent = new Intent(mContext, LocalImageSelectActivity.class);
            startActivity(intent);
        });
    }
}
