package com.example.whatspopin;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends Activity {

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_event);

		Button next = findViewById(R.id.button);
		next.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				TextView usr = findViewById(R.id.eventNameText);
				System.out.println(usr.getText());
				Intent intent = new Intent();
				setResult(RESULT_OK, intent);
				finish();
			}

		});
	}
}