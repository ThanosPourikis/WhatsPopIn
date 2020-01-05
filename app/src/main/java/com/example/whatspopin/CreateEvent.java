package com.example.whatspopin;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.Calendar;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CreateEvent extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

		Button next = findViewById(R.id.button);
		next.setOnClickListener((View view)->{
				Executor myExecutor = Executors.newSingleThreadExecutor();
				myExecutor.execute(() -> {
				TextView usr = findViewById(R.id.eventNameText);
				TextView loc = findViewById(R.id.locTextArea);
				TextView cat = findViewById(R.id.catTextArea);
				TextView desc = findViewById(R.id.descTextArea);
				Event event = new Event(usr.getText().toString(),loc.getText().toString(),cat.getText().toString(),desc.getText().toString());
				db.eventDao().insertEvent(event);


				});
				Intent intent = new Intent(view.getContext(),MainActivity.class);
				startActivity(intent);
				setResult(RESULT_OK, intent);
				finish();
			}

		);
	}
}