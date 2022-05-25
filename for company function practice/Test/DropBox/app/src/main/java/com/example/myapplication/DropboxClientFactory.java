package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.android.AuthActivity;
import com.dropbox.core.http.OkHttp3Requestor;
import com.dropbox.core.oauth.DbxCredential;
import com.dropbox.core.v2.DbxClientV2;

import java.lang.ref.WeakReference;

public class DropboxClientFactory {
    private static DbxClientV2 sDbxClient;
    private static LogoutHandler mHandler;


    public static void init(String accessToken) {
        if (sDbxClient == null) {
            DbxRequestConfig requestConfig = DbxRequestConfig.newBuilder("Example")
                    .withHttpRequestor(new OkHttp3Requestor(OkHttp3Requestor.defaultOkHttpClient()))
                    .build();

            sDbxClient = new DbxClientV2(requestConfig, accessToken);
        }
    }

    public static DbxClientV2 getClient() {
        if (sDbxClient == null) {
            throw new IllegalStateException("Client not initialized.");
        }
        return sDbxClient;
    }

    public static void logout(final DropboxClientFactory.Callback callback, Context context) {
        mHandler = new LogoutHandler(callback);
        new Thread() {
            public void run() {
                try {
                    sDbxClient.auth().tokenRevoke();
                } catch (DbxException e) {
                    e.printStackTrace();
                }

                mHandler.handleMessage(null, context);
            }
        }.start();
    }

    static class LogoutHandler extends Handler {
        private final WeakReference<DropboxClientFactory.Callback> mCallback;
        public LogoutHandler(DropboxClientFactory.Callback callback) {
            mCallback = new WeakReference<DropboxClientFactory.Callback>(callback);
        }

        public void handleMessage(Message msg, @NonNull Context context) {
            SharedPreferences pref = context.getSharedPreferences("DropBox", Context.MODE_PRIVATE);
            @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = pref.edit();
            editor.remove("DropBoxAccessToken").apply();
            sDbxClient = null;
            AuthActivity.result = null;

            DropboxClientFactory.Callback callback = mCallback.get();
            callback.finishLogout();
        }
    }

    interface Callback {
        void finishLogout();
    }

}
