package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Button btn = findViewById(R.id.login_button);
		btn.setOnClickListener((View v) -> {
				Intent myIntent = new Intent(v.getContext(), SavedActivity.class);
				startActivityForResult(myIntent, 0);

		});
	}

	}
