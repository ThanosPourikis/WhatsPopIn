package com.example.whatspopin.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;


@Entity(primaryKeys = {"eventId","email"})
public class Attends implements Serializable {
	private String eventId;
	@NonNull
	private String userId;

	public Attends(String eventId,String userId)
	{
		this.eventId = eventId;
		this.userId = userId;
	}

	public String getEventId() {return eventId;}

	@NonNull
	public String getUserId() {return userId;}

	@Exclude
	public HashMap<String, Object> toMap() {
		HashMap<String, Object> result = new HashMap<>();
		result.put("eventId",eventId);
		result.put("userId",userId);
		return result;
	}
}
