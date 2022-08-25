package com.example.addresssearchapidaum;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.addresssearchapidaum.databinding.ActivitySearchBinding;

public class SearchActivity extends AppCompatActivity {

    private ActivitySearchBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        initialize();
    }

    private void viewBinding(){
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initialize(){
        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.addJavascriptInterface(new BridgeInterface(), "Android");
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Android -> JS 함수 호출`
                binding.webView.loadUrl("javascript:sample2_execDaumPostcode();"); // 실제 HTML 안에 있는 메서드임 아무 이름 쓰면 안됨
            }
        });
        // 최초 웹뷰 로드 (Hosting 설정 한거)
        binding.webView.loadUrl("appadresssearch.firebaseapp.com");
    }

    private class BridgeInterface {
        @JavascriptInterface
        public void processData(String data){
            Intent intent = new Intent();
            intent.putExtra("data",data);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}