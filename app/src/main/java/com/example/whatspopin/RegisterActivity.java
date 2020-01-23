package com.example.whatspopin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.whatspopin.database.User;
import com.example.whatspopin.database.WhatsPopInDatabase;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RegisterActivity extends AppCompatActivity {

	final WhatsPopInDatabase db = WhatsPopInDatabase.getInstance(this);

	User user ;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);

		Button registerButton = findViewById(R.id.register_button);
		TextView emailTxt = findViewById(R.id.EmailText);
		TextView passwordTxt = findViewById(R.id.passwordText);
		TextView confirmTxt = findViewById(R.id.passwordConfirm);
		Executor myEX = Executors.newSingleThreadExecutor();


		registerButton.setOnClickListener((View v )->{
			String mail = emailTxt.getText().toString();
			String pass= passwordTxt.getText().toString();


			if(pass.equals(confirmTxt.getText().toString()))
			{
				myEX.execute(() ->
						db.userDao().insertUser(new User(mail,getHash(pass))));
			}
		});

	}
	String getHash(String pass)
	{
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] messageDigest = md.digest(pass.getBytes());

			BigInteger no = new BigInteger(1, messageDigest);

			String hashtext = no.toString(16);

			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;

		}catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException();
		}

	}
}
