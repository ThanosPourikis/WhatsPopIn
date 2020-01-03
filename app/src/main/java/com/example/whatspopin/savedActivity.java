package com.example.whatspopin;

import android.app.Activity;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class savedActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_events);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

		Executor myExecutor = Executors.newSingleThreadExecutor();
		myExecutor.execute(() -> {
			List<Event> ev = db.eventDao().getEventList();

			for(Event i :ev){
				System.out.println(i.getName());
				System.out.println(i.getCategory());
				System.out.println(i.getPlace());
				System.out.println(i.getDescription());

			}

		});




	}
}
