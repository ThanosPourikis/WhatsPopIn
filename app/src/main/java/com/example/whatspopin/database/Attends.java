package com.example.whatspopin.database;

import androidx.room.Entity;


@Entity(primaryKeys = "eventId,userId")
public class Attends {
	private int eventId;
	private int userId;


}
