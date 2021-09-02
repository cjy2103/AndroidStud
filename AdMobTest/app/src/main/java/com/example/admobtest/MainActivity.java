package com.example.admobtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;
    Button fullPageAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(MainActivity.this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                adMob();
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        fullPageAd = findViewById(R.id.btn_full_page_ad);

        fullPageAd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FullPageAdActivity.class);
            startActivity(intent);
            finish();
        });

    }

    /**
     * @DESC : 광고호출
     */
    private void adMob(){
        AdView adView = new AdView(MainActivity.this);

        adView.setAdSize(AdSize.BANNER);

        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");


    }
}