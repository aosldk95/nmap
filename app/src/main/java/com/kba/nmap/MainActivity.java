package com.kba.nmap;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.ArrowheadPathOverlay;
import com.naver.maps.map.overlay.Marker;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(this);


    }
    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        LatLng location = new LatLng(35.94528, 126.682167);
        CameraPosition cameraPosition = new CameraPosition(location, 8);
        naverMap.setCameraPosition(cameraPosition);
        Marker marker = new Marker();
        marker.setPosition(new LatLng(35.94528, 126.682167));
        marker.setMap(naverMap);
        Marker marker2 = new Marker();
        marker2.setPosition(new LatLng(35.969389, 126.957333));
        marker2.setMap(naverMap);
        Marker marker3 = new Marker();
        marker3.setPosition(new LatLng(35.983, 126.717));
        marker3.setMap(naverMap);
        Marker marker4 = new Marker();
        marker4.setPosition(new LatLng(35.846833, 127.129361));
        marker4.setMap(naverMap);
        ArrowheadPathOverlay arrowheadPath = new ArrowheadPathOverlay();
        arrowheadPath.setCoords(Arrays.asList(
                new LatLng(35.94528, 126.682167),
                new LatLng(35.983, 126.717),
                new LatLng(35.969389, 126.957333),
                new LatLng(35.846833, 127.129361)
        ));
        arrowheadPath.setMap(naverMap);
        
    }

}
