package com.example.whatspopin.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {
	@PrimaryKey(autoGenerate = true)
	private int userId;
	private String email;
	private String password;

	public User(int userId,String email,String password)
	{
		this.userId = userId;
		this.email = email;
		this.password = password;
	}

	@Ignore
	public User(String email,String password)
	{
		this.email = email;
		this.password = password;
	}




	public int getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
}