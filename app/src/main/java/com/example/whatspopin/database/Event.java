package com.example.whatspopin.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Event {
	@PrimaryKey(autoGenerate = true)
	private int eventId;
	@ColumnInfo(name = "name")
	private String name;
	@ColumnInfo(name = "place")
	private String place;
	@ColumnInfo(name = "category")
	private String category;
	@ColumnInfo(name = "description")
	private String description;

	public Event(int eventId,String name,String place,String category,String description)
	{
		this.eventId = eventId;
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;

	}
	@Ignore
	public Event(String name,String place,String category,String description)
	{
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;

	}

	public int getEventId() {
		return eventId;
	}

	public String getName() {
		return name;
	}

	public String getPlace() {
		return place;
	}

	public String getCategory() {
		return category;
	}

	public String getDescription() {
		return description;
	}
}
