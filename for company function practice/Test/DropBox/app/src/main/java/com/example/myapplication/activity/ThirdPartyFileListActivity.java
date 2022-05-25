package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.Metadata;
import com.example.myapplication.DropboxClientFactory;
import com.example.myapplication.R;
import com.example.myapplication.activity.dialog.CustomProgressDialog;
import com.example.myapplication.adapter.FileRecyclerAdapter;
import com.example.myapplication.databinding.ActivityThirdPartyFileListBinding;
import com.example.myapplication.task.DownloadFileTask;

import java.io.File;
import java.util.List;

public class ThirdPartyFileListActivity extends AppCompatActivity {

    private ActivityThirdPartyFileListBinding binding;

    private String account;
    private String path;

    private FileRecyclerAdapter adapter;

    private List list;

    private DownloadFileTask downloadFileTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();

        itemClick();
    }

    private void viewBinding(){
        binding = ActivityThirdPartyFileListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        path = getIntent().getStringExtra("path");
        if(path == null){
            path = "";
        }
//        adapter = new FileRecyclerAdapter(this); // TODO : loadData 쪽에서 adapter setting
    }

    private void itemClick(){
        adapter.setOnItemClickListener((v,pos) -> {
            Metadata item = (Metadata) list.get(pos);
            if(item instanceof FolderMetadata){
                Intent intent = new Intent(this, ThirdPartyFileListActivity.class);
                //intent.putExtra("email",account); // account가 뭐지?
                intent.putExtra("path",item.getPathLower());
                startActivity(intent);
            } else if(item instanceof FileMetadata){
                //TODO : CustomDialog 띄우기
                FragmentManager fm = getSupportFragmentManager();

                DialogFragment dialogFragment = new CustomProgressDialog();
                dialogFragment.show(fm,"customProgressDialog");

                downloadFileTask =  new DownloadFileTask(ThirdPartyFileListActivity.this, DropboxClientFactory.getClient(), new DownloadFileTask.Callback() {

                    @Override
                    public void onDownloadComplete(File result) {

                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        });
    }
}