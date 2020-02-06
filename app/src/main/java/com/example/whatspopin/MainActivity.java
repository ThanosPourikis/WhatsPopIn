package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;

import com.example.whatspopin.database.Event;
import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
	private DatabaseReference mPostReference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPostReference = FirebaseDatabase.getInstance().getReference().child("Events");

		setContentView(R.layout.activity_main);

		mPostReference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				ScrollViewFill.fill(findViewById(R.id.mainActEvList), dataSnapshot, 1);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener((View view) -> {

					Intent myIntent = new Intent(view.getContext(), CreateEvent.class);
					startActivityForResult(myIntent, 0);
				}
		);

		ImageView img = findViewById(R.id.profile);
		img.setOnClickListener((View view) -> {
					Intent myIntent = new Intent(view.getContext(), LoginActivity.class);
					startActivityForResult(myIntent, 0);

				}
		);


	}


}
