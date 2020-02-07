package com.example.whatspopin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
	private FirebaseAuth mAuth;
	private String TAG = "EmailPassword";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		mAuth = FirebaseAuth.getInstance();

		Button registerButton = findViewById(R.id.register_button);
		TextView emailTxt = findViewById(R.id.EmailText);
		TextView passwordTxt = findViewById(R.id.passwordText);
		TextView confirmTxt = findViewById(R.id.passwordConfirm);


		registerButton.setOnClickListener((View v) -> {

			String mail = emailTxt.getText().toString();
			String pass = passwordTxt.getText().toString();

			if (pass.equals(confirmTxt.getText().toString())) {
				mAuth.createUserWithEmailAndPassword(mail,pass)
						.addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
							@Override
							public void onComplete(@NonNull Task<AuthResult> task) {
								if(task.isSuccessful()) {
									Log.d(TAG, "User creation successful");
									startActivity(new Intent(getApplicationContext(),MainActivity.class));
								}
								else
									Log.d(TAG,"User creation failed" + task.getException().getMessage());
							}
						});
			}
		});

	}


}
