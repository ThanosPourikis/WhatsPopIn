package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent myIntent = new Intent(view.getContext(), CreateEvent.class);
				startActivityForResult(myIntent, 0);
			}
		});
		ImageView img =findViewById(R.id.profile);
		img.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view) {
				Intent myIntent = new Intent(view.getContext(), profileActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
		ImageView eventImg = findViewById(R.id.imgMainActivity);
		eventImg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(),contentMain.class);
				startActivity(myIntent);
			}
		});

	}



}
