package com.example.whatspopin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatspopin.database.WhatsPopInDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends AppCompatActivity {

	TextView failed ;
	private FirebaseAuth mAuth;
	private String TAG = "EmailPassword";




	public void onCreate(Bundle savedInstanceState) {
		failed = findViewById(R.id.failed);
		super.onCreate(savedInstanceState);
		mAuth = FirebaseAuth.getInstance();
		setContentView(R.layout.login);
		TextView email = findViewById(R.id.EmailText);
		TextView pass = findViewById(R.id.passwordText);

		Button loginBtn = findViewById(R.id.login_button);
		Button registerBtn = findViewById(R.id.register_button);
		FirebaseUser user = mAuth.getCurrentUser();
		if(user == null) {
			loginBtn.setOnClickListener((View v) -> {
				mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
						.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {
								if (task.isSuccessful()) {
									Log.d(TAG, "signInWithEmail:success");
									startActivity(new Intent(getApplicationContext(), MainActivity.class));
								} else {
									Log.w(TAG, "signInWithEmail:failure", task.getException());
									Toast.makeText(LoginActivity.this, "Authentication failed.",
											Toast.LENGTH_SHORT).show();
								}
							}
						});


			});
		}else{

			email.setVisibility(View.INVISIBLE);
			pass.setVisibility(View.INVISIBLE);
			registerBtn.setVisibility(View.INVISIBLE);
			findViewById(R.id.passText).setVisibility(View.INVISIBLE);
			findViewById(R.id.orText).setVisibility(View.INVISIBLE);
			TextView a = findViewById(R.id.mailTxt);
			a.setText(user.getEmail());
			loginBtn.setText("Log out");
			loginBtn.setOnClickListener((View v) -> {
				mAuth.signOut();
				startActivity(new Intent(getApplicationContext(), MainActivity.class));

			});
		}

		registerBtn.setOnClickListener((View v) -> {
			Intent myIntent = new Intent(v.getContext(), RegisterActivity.class);
			startActivity(myIntent);
			finish();
		});
	}

}
