package com.example.whatspopin;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

		List<Event> eventList =  db.eventDao().getEventList();
		LinearLayout ls = findViewById(R.id.mainActEvList);
		for(Event i : eventList){

			LinearLayout li = new LinearLayout(this);
			li.setOrientation(LinearLayout.HORIZONTAL);
			ImageView img = new ImageView(this);
			img.setImageResource(R.drawable.icon);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
			img.setLayoutParams(layoutParams);

			LinearLayout li2 = new LinearLayout(this);
			li2.setOrientation(LinearLayout.VERTICAL);
			TextView txt = new TextView(this);
			txt.setText(i.getName());
			li2.addView(txt);

			txt = new TextView(this);
			txt.setText(i.getCategory());
			li2.addView(txt);

			li.addView(img);
			li.addView(li2);
			ls.addView(li);

		}







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
