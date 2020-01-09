package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SavedActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_events);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);


		Executor myEx = Executors.newSingleThreadExecutor();
		myEx.execute(()->
			ScrollViewFill.fill(findViewById(R.id.savedActEvList),db.eventDao().getEventList(),2)
		);
		TextView txt = findViewById(R.id.titleSavedEvents);

		txt.setOnClickListener((View v) ->
		{		Intent myIntent = new Intent(v.getContext(), MainActivity.class);
				startActivityForResult(myIntent, 0);
				finish();
		});



	}
}
