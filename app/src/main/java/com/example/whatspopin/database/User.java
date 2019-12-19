package com.example.whatspopin.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
	@PrimaryKey(autoGenerate = true)
	private int userId;
	private String email;
	private String password;

}