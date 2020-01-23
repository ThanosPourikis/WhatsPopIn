package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.WhatsPopInDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity
{
	final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);
	int flagUsr = 0;


	public void onCreate(Bundle savedInstanceState) {
		Executor myEx = Executors.newSingleThreadExecutor();
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);
		TextView email = findViewById(R.id.EmailText);
		TextView pass = findViewById(R.id.passwordText);

		Button loginBtn = findViewById(R.id.login_button);
		Button registerBtn = findViewById(R.id.register_button);

		loginBtn.setOnClickListener((View v) -> {
			myEx.execute(()->{
				String mail = null;
				mail = db.userDao().getEmail(email.getText().toString()) ;
				System.out.println(mail);
				if(mail!= null)
				{
					String dbPass = null;
					dbPass = db.userDao().getPass(mail);
					if(pass.getText().toString().equals(dbPass))
					{
						runOnUiThread(() ->{
						Intent myIntent = new Intent(v.getContext(), SavedActivity.class);
						startActivityForResult(myIntent,0);
						finish();
						});
					}
				}

			});



		});

		registerBtn.setOnClickListener((View v) -> {
			Intent myIntent = new Intent(v.getContext(),RegisterActivity.class);
			startActivity(myIntent);
			finish();
		});
	}

}
