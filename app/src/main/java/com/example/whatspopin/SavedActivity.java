package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SavedActivity extends Activity {
	private DatabaseReference reference;
	private TextView txt;
	private TextView signout;
	private FirebaseAuth mAuth;
	private FirebaseUser user;
	private LinearLayout savedView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.saved_events);
		txt = findViewById(R.id.titleSavedEvents);
		signout = findViewById(R.id.signout);
		mAuth = FirebaseAuth.getInstance();
		user = mAuth.getCurrentUser();
		reference = FirebaseDatabase.getInstance().getReference().child("Events");
		savedView =findViewById(R.id.savedActEvList);
		reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				savedView.removeAllViews();
				ScrollViewFill.fill(savedView, dataSnapshot, 0);

			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

		txt.setOnClickListener((View v) ->
		{
			Intent myIntent = new Intent(v.getContext(), MainActivity.class);
			startActivity(myIntent);
			finish();
		});

		signout.setOnClickListener((View v ) ->{
			mAuth.signOut();
			startActivity(new Intent(getApplicationContext(),MainActivity.class));
			finish();
		});


	}
}
