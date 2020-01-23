package com.example.whatspopin.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User implements Serializable {
	@PrimaryKey
	@NonNull
	private String email;
	private String password;

	public User(String email,String password)
	{
		this.email = email;
		this.password = password;
	}




	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}