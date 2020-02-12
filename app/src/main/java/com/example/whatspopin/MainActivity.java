package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

	private DatabaseReference reference;
	private FirebaseAuth mAuth;
	private LinearLayout mainView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		reference = FirebaseDatabase.getInstance().getReference().child("Events");

		mAuth = FirebaseAuth.getInstance();
		setContentView(R.layout.activity_main);
		mainView = findViewById(R.id.mainActEvList);
		//Pare ta teleutaia apo ta Events pou den exoun teliosi idi
		reference.orderByChild("date").startAt(System.currentTimeMillis()).limitToLast(10).addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				mainView.removeAllViews();
				ScrollViewFill.fill(mainView, dataSnapshot, 1);
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener((View view) -> {
					FirebaseUser user = mAuth.getCurrentUser();
					if (user == null) {
						Toast.makeText(MainActivity.this, getResources().getString(R.string.loggedIn), Toast.LENGTH_LONG).show();
					} else {
						startActivity(new Intent(view.getContext(), CreateEvent.class));
					}
				}
		);

		ImageView img = findViewById(R.id.profile);
		img.setOnClickListener((View view) -> startActivity(new Intent(view.getContext(), LoginActivity.class)));

	}


}
