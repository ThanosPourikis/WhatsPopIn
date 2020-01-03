package com.example.whatspopin.database;

import androidx.room.Entity;


@Entity(primaryKeys = {"eventId","userId"})
public class Attends {
	private int eventId;
	private int userId;

	public Attends(int eventId,int userId)
	{
		this.eventId = eventId;
		this.userId = userId;
	}

	public int getEventId() {
		return eventId;
	}

	public int getUserId() {
		return userId;
	}
}
