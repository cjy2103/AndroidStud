package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.dropbox.core.android.Auth;
import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.oauth.DbxCredential;
import com.example.myapplication.BuildConfig;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.util.LogUtil;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        clickLogin();

        clickShowList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        int type = 0;
        SharedPreferences pref = getSharedPreferences("DropBox",MODE_PRIVATE);
        String accessToken = pref.getString("DropBoxAccessToken", null);

        if(accessToken == null){
            accessToken = Auth.getOAuth2Token();
            if(accessToken != null){
                @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
                editor.putString("DropBoxAccessToken",accessToken);
                editor.apply();
                type = 1;
            }
        } else {
            type = 1;
        }

        updateUi(type, accessToken);
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void clickLogin(){
        binding.btnLogin.setOnClickListener(v->{
            LogUtil.log("값은?"+BuildConfig.MANIFEST_DROPBOX_API_KEY);
            Auth.startOAuth2Authentication(this, BuildConfig.DROPBOX_API_KEY); // TODO : 암호화
        });
    }

    private void clickShowList(){
        binding.btnShowList.setOnClickListener(v->{
            Intent intent = new Intent(this, ThirdPartyFileListActivity.class); // TODO
            startActivity(intent);
        });
    }

    private void updateUi(final int type, String accessToken){
        if(type == 0){
            binding.btnLogin.setVisibility(View.VISIBLE);
            binding.btnShowList.setVisibility(View.GONE);
            binding.btnLogout.setVisibility(View.GONE);
        } else {
            binding.btnLogin.setVisibility(View.GONE);
            binding.btnShowList.setVisibility(View.VISIBLE);
            binding.btnLogut.setVisibility(View.VISIBLE);

            //DropBoxClienFactory.init(accessToken); // TODO
        }
    }


}