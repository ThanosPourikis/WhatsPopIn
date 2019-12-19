package com.example.whatspopin;

import android.app.Activity;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.List;

public class savedActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_events);
		WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);


		List<Event> ev = db.eventDao().getEventList();


		for(Event i :ev){
			System.out.println(i.getName());
			System.out.println(i.getCategory());
			System.out.println(i.getPlace());
			System.out.println(i.getDescription());

		}

	}
}
