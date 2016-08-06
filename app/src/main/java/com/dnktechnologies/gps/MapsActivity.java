package com.dnktechnologies.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    EditText edit_lati, edit_longi;
    private GoogleMap mMap;
    double latitude, longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        edit_lati = (EditText) findViewById(R.id.edit_lati);
        edit_longi = (EditText) findViewById(R.id.edit_longi);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {


        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(cameraPosition.target).title(cameraPosition.toString()));
                latitude=cameraPosition.target.latitude;
                longitude=cameraPosition.target.longitude;
                Log.i("lati",""+latitude);
                edit_lati.setText(Double.toString(latitude));
                edit_longi.setText(Double.toString(longitude));

            }
        });
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions().position(latLng).title(latLng.toString()));
                latitude=latLng.latitude;
                longitude=latLng.longitude;
                edit_lati.setText(Double.toString(latitude));
                edit_longi.setText(Double.toString(longitude));

            }
        });

    }
}
