package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);
		Executor myEx = Executors.newSingleThreadExecutor();

		myEx.execute(() -> {
			List<Event> ev = db.eventDao().getEventList();
			runOnUiThread(() ->
					ScrollViewFill.fill(findViewById(R.id.mainActEvList), ev, 1)
			);

		});

		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener((View view) -> {

					Intent myIntent = new Intent(view.getContext(), CreateEvent.class);
					startActivityForResult(myIntent, 0);
				}
		);

		ImageView img = findViewById(R.id.profile);
		img.setOnClickListener((View view) -> {
					Intent myIntent = new Intent(view.getContext(), ProfileActivity.class);
					startActivityForResult(myIntent, 0);

				}
		);


	}


}
