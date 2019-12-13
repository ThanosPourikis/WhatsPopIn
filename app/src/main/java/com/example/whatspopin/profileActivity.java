package com.example.whatspopin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class profileActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		Button btn = findViewById(R.id.loginBtn);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent myIntent = new Intent(v.getContext(), savedActivity.class);
				startActivityForResult(myIntent, 0);
			}
		});
	}

	}
