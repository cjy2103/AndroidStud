package com.example.myapplication.activity.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.DropboxClientFactory;
import com.example.myapplication.R;
import com.example.myapplication.activity.ThirdPartyFileListActivity;
import com.example.myapplication.databinding.CustomProgressDialogBinding;
import com.example.myapplication.task.DownloadFileTask;

import java.io.File;

public class CustomProgressDialog extends DialogFragment {

    private CustomProgressDialogBinding binding;

    private DownloadFileTask downloadFileTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.custom_progress_dialog, container, false);
//        return viewBinding(inflater,container,savedInstanceState);
    }

    private View viewBinding(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = CustomProgressDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

}
