package com.example.whatspopin;

import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.MapView;

public class contentMain extends Activity implements OnMapReadyCallback {
	private GoogleMap gmap;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_main);

//		MapView map = findViewById(R.id.mapView);


	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		gmap = googleMap;
		gmap.setMinZoomPreference(12);
		LatLng ny = new LatLng(40.7143528, -74.0059731);
	}
}
