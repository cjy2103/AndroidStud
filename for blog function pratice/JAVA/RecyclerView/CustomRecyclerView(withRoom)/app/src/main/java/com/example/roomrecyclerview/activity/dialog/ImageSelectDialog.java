package com.example.roomrecyclerview.activity.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.roomrecyclerview.R;
import com.example.roomrecyclerview.activity.LocalImageSelectActivity;
import com.example.roomrecyclerview.databinding.DialogImageSelectBinding;

public class ImageSelectDialog extends DialogFragment {

    private DialogImageSelectBinding binding;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return viewBinding(inflater, container, savedInstanceState);
    }

    private View viewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DialogImageSelectBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickClose();

        clickLocal();
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
