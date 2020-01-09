package com.example.whatspopin;

import android.app.Activity;
import android.os.Bundle;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

public class ContentMain extends Activity implements OnMapReadyCallback {
	private MapView mapView;
	private GoogleMap gmap;

	private static final String MAP_VIEW_BUNDLE_KEY = "";
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_main);


		Bundle mapViewBundle = null;
		if (savedInstanceState != null) {
			mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
		}

		mapView = findViewById(R.id.mapView);
		mapView.onCreate(mapViewBundle);
		mapView.getMapAsync(this);

	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

		Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
		if (mapViewBundle == null) {
			mapViewBundle = new Bundle();
			outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
		}

		mapView.onSaveInstanceState(mapViewBundle);
	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		gmap = googleMap;
		LatLng ny = new LatLng(40.7143528, -74.0059731);
	}
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	protected void onStart() {
		super.onStart();
		mapView.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
		mapView.onStop();
	}
	@Override
	protected void onPause() {
		mapView.onPause();
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		mapView.onDestroy();
		super.onDestroy();
	}
	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mapView.onLowMemory();
	}
}
