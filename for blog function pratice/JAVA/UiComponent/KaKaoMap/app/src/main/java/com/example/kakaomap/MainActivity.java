package com.example.kakaomap;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.ViewGroup;

import com.example.kakaomap.databinding.ActivityMainBinding;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MapView mapView;
    ViewGroup mapViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding();

        init();

        markerAdd();
    }

    private void viewBinding(){
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void init(){
        mapView = new MapView(this);
        mapViewContainer = binding.mapView;
        mapViewContainer.addView(mapView);
    }

    private void markerAdd(){
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(35.1399, 129.0986), true);
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(35.1399, 129.0986);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("경성대학교");
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker); // 지도위에 마커 추가
    }



}