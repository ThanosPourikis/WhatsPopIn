package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.whatspopin.database.Event;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;


public class ContentMain extends Activity implements OnMapReadyCallback {
	private MapView mapView;
	private GoogleMap gmap;

	TextView title;
	TextView dateAndTime;
	TextView desc;


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.content_main);

		Event event = (Event) getIntent().getSerializableExtra("eventObj");

		title = findViewById(R.id.contentMainTitle);
		title.setText(event.getName());
		desc = findViewById(R.id.contentMainDesc);
		desc.setText(event.getDescription());
		dateAndTime = findViewById(R.id.dateAndTime);
		dateAndTime.setText(new SimpleDateFormat("dd/MM/YY").format(event.getDate())
				+ " At " + event.getTime());

		ImageView profile = findViewById(R.id.profile);
		profile.setOnClickListener((View v) -> {
			Intent myIntent = new Intent(v.getContext(), ProfileActivity.class);
			startActivityForResult(myIntent, 0);
			finish();
		});


		mapView = findViewById(R.id.mapView);
		mapView.onCreate(savedInstanceState);
		mapView.getMapAsync(this);


	}

	@Override
	public void onMapReady(GoogleMap googleMap) {
		gmap = googleMap;
		LatLng papel = new LatLng(38.219252, 21.746566);


		gmap.addMarker(new MarkerOptions().position(papel));
		gmap.moveCamera(CameraUpdateFactory.newLatLngZoom(papel, 12));
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
