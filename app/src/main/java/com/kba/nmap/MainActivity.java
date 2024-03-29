package com.kba.nmap;

        import androidx.annotation.NonNull;
        import androidx.annotation.UiThread;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.Toast;

        import com.naver.maps.geometry.LatLng;
        import com.naver.maps.geometry.LatLngBounds;
        import com.naver.maps.map.CameraPosition;
        import com.naver.maps.map.CameraUpdate;
        import com.naver.maps.map.MapFragment;
        import com.naver.maps.map.NaverMap;
        import com.naver.maps.map.OnMapReadyCallback;
        import com.naver.maps.map.UiSettings;
        import com.naver.maps.map.overlay.ArrowheadPathOverlay;
        import com.naver.maps.map.overlay.InfoWindow;
        import com.naver.maps.map.overlay.Marker;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

        import static com.naver.maps.map.NaverMap.LAYER_GROUP_BUILDING;

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
        List listA = new ArrayList();
        listA.add(new LatLng(35.94528, 126.682167));
        listA.add(new LatLng(35.983, 126.717));
        listA.add(new LatLng(35.969389, 126.957333));
        listA.add(new LatLng(35.846833, 127.129361));
        LatLngBounds bounds = new LatLngBounds.Builder()
                .include(listA)
                .build();
        CameraUpdate cameraUpdate = CameraUpdate.fitBounds(bounds,200);
        naverMap.moveCamera(cameraUpdate);
        List markers = new ArrayList();
        List windows = new ArrayList();
        List texts = new ArrayList();

        Marker marker = new Marker();
        marker.setPosition((LatLng) listA.get(0));
        marker.setMap(naverMap);
        Marker marker1 = new Marker();
        marker1.setPosition((LatLng) listA.get(1));
        marker1.setMap(naverMap);
        Marker marker2 = new Marker();
        marker2.setPosition((LatLng) listA.get(2));
        marker2.setMap(naverMap);
        Marker marker3 = new Marker();
        marker3.setPosition((LatLng) listA.get(3));
        marker3.setMap(naverMap);
        ArrowheadPathOverlay arrowheadPath = new ArrowheadPathOverlay();
        arrowheadPath.setCoords(listA);
        Context context = this;
        arrowheadPath.setMap(naverMap);
        InfoWindow infoWindow = new InfoWindow();
        infoWindow.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산대";
            }
        });
        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "군산시청";
            }
        });
        InfoWindow infoWindow2 = new InfoWindow();
        infoWindow2.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "원광대";
            }
        });
        InfoWindow infoWindow3 = new InfoWindow();
        infoWindow3.setAdapter(new InfoWindow.DefaultTextAdapter(context) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "전북대";
            }
        });
        infoWindow.open(marker);
        infoWindow1.open(marker1);
        infoWindow2.open(marker2);
        infoWindow3.open(marker3);
        naverMap.setOnMapLongClickListener((point, coord) ->
                Toast.makeText(this, coord.latitude + ", " + coord.longitude,
                        Toast.LENGTH_SHORT).show());
        UiSettings uiSettings = naverMap.getUiSettings();
        naverMap.setMapType(NaverMap.MapType.Hybrid);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            int count = 1;

            @Override

            public void onClick(View view) {
                if (count % 2 == 1) {
                    marker.setMap(null);
                    marker1.setMap(null);
                    marker2.setMap(null);
                    marker3.setMap(null);
                    infoWindow.close();
                    infoWindow1.close();
                    infoWindow2.close();
                    infoWindow3.close();
                    arrowheadPath.setMap(null);
                    count += 1;
                }
                else {
                    marker.setMap(naverMap);
                    marker1.setMap(naverMap);
                    marker2.setMap(naverMap);
                    marker3.setMap(naverMap);
                    infoWindow.open(marker);
                    infoWindow1.open(marker1);
                    infoWindow2.open(marker2);
                    infoWindow3.open(marker3);
                    arrowheadPath.setMap(naverMap);
                    count += 1;

                }
            }


        });
        Button btn1 = findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naverMap.setMapType(NaverMap.MapType.Basic);
            }

        });
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naverMap.setMapType(NaverMap.MapType.Navi);
            }

        });
        Button btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naverMap.setMapType(NaverMap.MapType.Satellite);
            }

        });
        Button btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naverMap.setMapType(NaverMap.MapType.Hybrid);
            }

        });
        Button btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                naverMap.setMapType(NaverMap.MapType.Terrain);
            }

        });

    }

}
