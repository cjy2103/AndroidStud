package com.example.gpsstate;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;


import com.example.gpsstate.databinding.ActivityMainBinding;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LocationManager locationManager;

    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private static final int REQUEST_CHECK_SETTINGS = 214;
    private static final int REQUEST_ENABLE_GPS = 516;

    private final ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == REQUEST_CHECK_SETTINGS){
                    switch (result.getResultCode()){
                        case Activity.RESULT_OK:
                            //Success Perform Task Here
                            Log.e("GPS","Success");
                            break;
                        case Activity.RESULT_CANCELED:
                            Log.e("GPS","User denied to access location");
                            openGpsEnableSetting();
                            break;
                    }
                } else if (result.getResultCode() == REQUEST_ENABLE_GPS) {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    boolean isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

                    if (!isGpsEnabled) {
                        openGpsEnableSetting();
                    } else {
                        Log.v("GPS","활성화상태");
//                        navigateToUser();
                    }
                }
            }
    );

    private void openGpsEnableSetting() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        resultLauncher.launch(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();
        initialize();

        clickCheck();
        clickLibrary();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initialize(){
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
    }

    private void clickCheck(){
        binding.btnCheck.setOnClickListener(v->{
            if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                // 위치정보 설정 Intent

                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        });
    }

    private void clickLibrary(){
        binding.btnLibrary.setOnClickListener(v->{
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
            builder.addLocationRequest(LocationRequest.create());
            builder.setAlwaysShow(true);
            mLocationSettingsRequest = builder.build();

            mSettingsClient = LocationServices.getSettingsClient(MainActivity.this);

            mSettingsClient
                    .checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener(locationSettingsResponse -> {
                        //Success Perform Task Here
                    })
                    .addOnFailureListener(e -> {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult(MainActivity.this, REQUEST_CHECK_SETTINGS);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.e("GPS","Unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                Log.e("GPS","Location settings are inadequate, and cannot be fixed here. Fix in Settings.");
                        }
                    })
                    .addOnCanceledListener(() -> Log.e("GPS","checkLocationSettings -> onCanceled"));
        });
    }

}