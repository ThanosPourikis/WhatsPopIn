package com.example.whatspopin.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Event implements Serializable {
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
	@ColumnInfo(name = "creatorId")
	private int creatorId;
	@ColumnInfo(typeAffinity = ColumnInfo.BLOB,name = "image")
	private byte[] image;



	public Event(int eventId, String name, String place, String category, String description,int creatorId,byte[] image)
	{
		this.eventId = eventId;
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;
		this.creatorId = creatorId;
		this.image = image;

	}
	@Ignore
	public Event(String name,String place,String category,String description,byte[] image)
	{
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;
		this.image = image;

	}

	public int getEventId() {
		return eventId;
	}

	public String getName() {
		return name;
	}

	public String getPlace() { return place; }

	public String getCategory() { return category; }

	public String getDescription() { return description;	}

	public int getCreatorId() {	return creatorId;}

	public byte[] getImage() {return image;}
}

