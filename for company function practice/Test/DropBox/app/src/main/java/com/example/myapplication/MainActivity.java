package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.dropbox.core.android.Auth;
import com.dropbox.core.json.JsonReadException;
import com.dropbox.core.oauth.DbxCredential;
import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.List;

public abstract class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final static boolean USE_SLT = true; //If USE_SLT is set to true, our Android example


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences prefs = getSharedPreferences("dropbox-sample", MODE_PRIVATE);

        if (USE_SLT) {
            String serailizedCredental = prefs.getString("credential", null);

            if (serailizedCredental == null) {
                DbxCredential credential = Auth.getDbxCredential();

                if (credential != null) {
                    prefs.edit().putString("credential", credential.toString()).apply();
                    initAndLoadData(credential);
                }
            } else {
                try {
                    DbxCredential credential = DbxCredential.Reader.readFully(serailizedCredental);
                    initAndLoadData(credential);
                } catch (JsonReadException e) {
                    throw new IllegalStateException("Credential data corrupted: " + e.getMessage());
                }
            }

        } else {
            String accessToken = prefs.getString("access-token", null);
            if (accessToken == null) {
                accessToken = Auth.getOAuth2Token();
                if (accessToken != null) {
                    prefs.edit().putString("access-token", accessToken).apply();
                    initAndLoadData(accessToken);
                }
            } else {
                initAndLoadData(accessToken);
            }
        }

        String uid = Auth.getUid();
        String storedUid = prefs.getString("user-id", null);
        if (uid != null && !uid.equals(storedUid)) {
            prefs.edit().putString("user-id", uid).apply();
        }
    }

    private void initAndLoadData(String accessToken) {
        DropboxClientFactory.init(accessToken);
        PicassoClient.init(getApplicationContext(), DropboxClientFactory.getClient());
        loadData();
    }

    private void initAndLoadData(DbxCredential dbxCredential) {
        DropboxClientFactory.init(dbxCredential);
        PicassoClient.init(getApplicationContext(), DropboxClientFactory.getClient());
        loadData();
    }

    protected abstract void loadData();

    protected boolean hasToken() {
        SharedPreferences prefs = getSharedPreferences("dropbox-sample", MODE_PRIVATE);
        if (USE_SLT) {
            return prefs.getString("credential", null) != null;
        } else {
            String accessToken = prefs.getString("access-token", null);
            return accessToken != null;
        }
    }

    public static void startOAuth2Authentication(Context context, String app_key, List<String> scope) {
        if (USE_SLT) {
            Auth.startOAuth2PKCE(context, app_key, DbxRequestConfigFactory.getRequestConfig(), scope);
        } else {
            Auth.startOAuth2Authentication(context, app_key);
        }
    }
}