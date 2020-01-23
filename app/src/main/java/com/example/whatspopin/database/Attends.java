package com.example.whatspopin.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import java.io.Serializable;


@Entity(primaryKeys = {"eventId","email"})
public class Attends implements Serializable {
	private int eventId;
	@NonNull
	private String email;

	public Attends(int eventId,String email)
	{
		this.eventId = eventId;
		this.email = email;
	}

	public int getEventId() {
		return eventId;
	}

	public String getEmail() {return email;}
}
