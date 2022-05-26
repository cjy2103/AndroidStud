package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
import com.example.myapplication.DropboxClientFactory;
import com.example.myapplication.R;
import com.example.myapplication.activity.dialog.CustomProgressDialog;
import com.example.myapplication.adapter.FileRecyclerAdapter;
import com.example.myapplication.databinding.ActivityThirdPartyFileListBinding;
import com.example.myapplication.task.DownloadFileTask;
import com.example.myapplication.task.GetCurrentAccountTask;
import com.example.myapplication.task.ListFolderTask;
import com.example.myapplication.util.LogUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences("DropBox",MODE_PRIVATE);
        String accessToken = pref.getString("DropBoxAccessToken", null);
        initAndLoadData(accessToken);
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

        list = new ArrayList<>();

        binding.fileRecycler.setLayoutManager(new LinearLayoutManager(this));

//        adapter = new FileRecyclerAdapter(this); // TODO : loadData 쪽에서 adapter setting
    }

    private void itemClick(){
        adapter.setOnItemClickListener((v,pos) -> {
            Metadata item = (Metadata) list.get(pos);
            if(item instanceof FolderMetadata){
                Intent intent = new Intent(this, ThirdPartyFileListActivity.class);
                intent.putExtra("email",account); // account가 뭐지?
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
                        dialogFragment.dismiss();
                        if(result != null){
                            LogUtil.log("성공");
                            Toast.makeText(ThirdPartyFileListActivity.this, "파일다운로드가 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                            viewFileInExternalApp(result);
                        } else {
                            LogUtil.log("다운로드 받을 파일 없음");
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        dialogFragment.dismiss();

                        LogUtil.log("다운로드 실패");
                        Toast.makeText(ThirdPartyFileListActivity.this, "에러발생", Toast.LENGTH_SHORT).show();
                    }
                });
                downloadFileTask.execute((FileMetadata) item);
            }
        });
    }

    private void viewFileInExternalApp(File result){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        String ext = result.getName().substring(result.getName().indexOf(".") + 1);
        String type = mime.getMimeTypeFromExtension(ext);

        Uri tempUri = FileProvider.getUriForFile(this,
                "com.example.android.fileprovider",
                result);
        intent.setDataAndType(tempUri, type);

        // 외부에서 해당 URI를 접근할 수 있도록 한다.
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        // Check for a handler first to avoid a crash
        Intent chooser = Intent.createChooser(intent, "Open File");
        try {
            startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            // Instruct the user to install a PDF reader here, or something
        }


//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        MimeTypeMap mime = MimeTypeMap.getSingleton();
//        String ext = result.getName().substring(result.getName().indexOf(".") + 1);
//        String type = mime.getMimeTypeFromExtension(ext);
//
//        intent.setDataAndType(Uri.fromFile(result), type);
//
//        // Check for a handler first to avoid a crash
//        PackageManager manager = getPackageManager();
//        @SuppressLint("QueryPermissionsNeeded") List<ResolveInfo> resolveInfo = manager.queryIntentActivities(intent, 0);
//        if (resolveInfo.size() > 0) {
//            startActivity(intent);
//        }
    }

    private void initAndLoadData(String accessToken){
        DropboxClientFactory.init(accessToken);
        loadData();
    }

    private void loadData(){
        if(account == null || account.length() == 0){
            new GetCurrentAccountTask(DropboxClientFactory.getClient(), new GetCurrentAccountTask.Callback() {
                @Override
                public void onComplete(FullAccount result) {
                    account = result.getEmail();
                    LogUtil.log("account값은?" + account);
                }

                @Override
                public void onError(Exception e) {
                    LogUtil.log(getClass().getName(),"고객 정보 가져오기 실패: "+e);
                }
            }).execute();
        }

        if(list.size() == 0){
            FragmentManager fm = getSupportFragmentManager();

            DialogFragment dialogFragment = new CustomProgressDialog();
            dialogFragment.show(fm,"LoadDataProgressDialog");

            new ListFolderTask(DropboxClientFactory.getClient(), new ListFolderTask.Callback() {
                @Override
                public void onDataLoaded(ListFolderResult result) {
                    dialogFragment.dismiss();

                    list = Collections.unmodifiableList(result.getEntries());
                    adapter = new FileRecyclerAdapter(ThirdPartyFileListActivity.this, list);
                    binding.fileRecycler.setAdapter(adapter);
                    LogUtil.log("정상출력");
                    LogUtil.log("리스트의 크기는?"+list.size());
                    itemClick();
                }

                @Override
                public void onError(Exception e) {
                    LogUtil.log("에러발생");
                }
            }).execute(path);
        }

    }
}