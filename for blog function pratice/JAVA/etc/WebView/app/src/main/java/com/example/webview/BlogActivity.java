package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webview.databinding.ActivityBlogBinding;

public class BlogActivity extends AppCompatActivity {

    private ActivityBlogBinding binding;
    private String url = "https://m.blog.naver.com/cjy2103?proxyReferer=https%3A%2F%2Fm.search.naver.com%2Fsearch.naver%3Fsm%3Dmtp_hty.top%26where%3Dm%26query%3D%25EC%2595%2588%25EB%2593%259C%25EB%25A1%259C%25EC%259D%25B4%25EB%2593%259C%2B%25EC%259D%2591%25EC%2595%25A0%25EA%25B0%259C%25EB%25B0%259C%25EC%259E%2590";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();
    }

    private void viewBinding(){
        binding = ActivityBlogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initialize(){
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.loadUrl(url);
        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient());
    }

    /**
     * @DESC: 웹뷰에서 뒤로가기 눌렀을때 이전 페이지로 이동되게.
     * 이거 안하면 창이 닫힘
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if((keyCode == KeyEvent.KEYCODE_BACK) && binding.webView.canGoBack()){
            binding.webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}