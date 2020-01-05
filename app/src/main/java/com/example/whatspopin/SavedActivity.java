package com.example.whatspopin;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SavedActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_events);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

		LinearLayout ls = findViewById(R.id.savedActEvList);
		List<Event> eventList;
		Callable<List<Event>> task = () -> db.eventDao().getEventList();
		Future<List<Event>> future = Executors.newCachedThreadPool().submit(task);
		try {
			eventList = future.get();
			ScrollViewFill.fill(ls,eventList);
		} catch (Exception e) {
			e.printStackTrace();
		}




	}
}
