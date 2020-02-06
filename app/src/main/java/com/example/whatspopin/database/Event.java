package com.example.whatspopin.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.firebase.database.Exclude;

import java.io.Serializable;
import java.util.HashMap;

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
	@ColumnInfo(name = "date")
	private long date;
	@ColumnInfo(name = "time")
	private String time;

	@Ignore
	public Event(){

	}
	public Event(int eventId, String name, String place, String category, String description,long date,String time,int creatorId,byte[] image)
	{
		this.eventId = eventId;
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;
		this.creatorId = creatorId;
		this.date = date;
		this.time = time;
		this.image = image;

	}
	@Ignore
	public Event(String name,String place,String category,String description,long date,String time,byte[] image)
	{
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;
		this.date = date;
		this.time = time;
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

	public long getDate() {return date;}

	public String getTime() {return time;}

	@Exclude
	public HashMap<String, Object> toMap(){
		HashMap<String,Object> result = new HashMap<>();
		result.put("name",name);
		result.put("place",place);
		result.put("category",category);
		result.put("description",description);
		result.put("creatorId",creatorId);
		result.put("image",image);
		result.put("time",time);
		return result;
	}
}

