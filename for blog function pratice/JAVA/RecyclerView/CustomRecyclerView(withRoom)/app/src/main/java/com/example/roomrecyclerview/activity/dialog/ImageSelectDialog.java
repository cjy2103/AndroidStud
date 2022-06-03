package com.example.roomrecyclerview.activity.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
    private ActivityResultLauncher<Intent> resultLauncher;
    private int imageCase = 0;

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
        switch (imageCase){
            case 0 :
                break;
            case 1 :
                insertActivity.albumSelectCallback(true);
                break;
            case 2 :
                insertActivity.albumSelectCallback(false);
                break;
        }
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
        boolean localImageGet = sharedPreferences.getBoolean("localSelect",false);
        if(localImageGet){
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("localSelect",false);
            editor.apply();
            imageCase = 1;
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

        initilaize();

        clickClose();

        clickLocal();

        clickGallery();
    }

    private void initilaize(){
        insertActivity = (InsertActivity)mActivity;
        galleryCallback();
    }

    private void galleryCallback(){
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result ->{
                    if(result.getResultCode() == Activity.RESULT_OK){
                        if(result.getData() == null){
                            return;
                        }
                        String imagePath = result.getData().getDataString();
                        LogUtil.log("이미지 경로:"+imagePath);
                        SharedPreferences sharedPreferences = mContext.getSharedPreferences("image",Context.MODE_PRIVATE);
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("path",imagePath);
                        editor.apply();
                        imageCase = 2;
                        dismiss();
                    }
                }
        );
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

    private void clickGallery(){
        binding.btnGallerty.setOnClickListener(v->{
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            resultLauncher.launch(intent);
        });
    }
}
