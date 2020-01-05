package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

		LinearLayout ls = findViewById(R.id.mainActEvList);
		List<Event> eventList;
		Callable<List<Event>> task = () -> db.eventDao().getEventList();
		Future<List<Event>> future = Executors.newCachedThreadPool().submit(task);
		try {
			eventList = future.get();
			ScrollViewFill.fill(ls,eventList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener((View view) ->{

				Intent myIntent = new Intent(view.getContext(), CreateEvent.class);
				startActivityForResult(myIntent, 0);
			}
		);

		ImageView img =findViewById(R.id.profile);
		img.setOnClickListener((View view) ->{
				Intent myIntent = new Intent(view.getContext(), ProfileActivity.class);
				startActivityForResult(myIntent, 0);
			}
		);



	}



}
