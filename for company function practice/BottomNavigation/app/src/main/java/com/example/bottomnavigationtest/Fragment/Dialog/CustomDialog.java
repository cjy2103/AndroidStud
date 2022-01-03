package com.example.bottomnavigationtest.Fragment.Dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import com.example.bottomnavigationtest.R;

public class CustomDialog extends DialogFragment {

    Button btnClose;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_dialog, container, false);

        clickClose(view);

        return view;
    }

    private void clickClose(View view){
        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v->{
            dismiss();
        });
    }
}
