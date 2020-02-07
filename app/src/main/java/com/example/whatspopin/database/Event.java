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
	@ColumnInfo(name = "name")
	private String name;
	@ColumnInfo(name = "place")
	private String place;
	@ColumnInfo(name = "category")
	private String category;
	@ColumnInfo(name = "description")
	private String description;
	@ColumnInfo(name = "creatorId")
	private String creatorId;
	@ColumnInfo(name = "imagePath")
	private String imagePath;
	@ColumnInfo(name = "date")
	private long date;
	@ColumnInfo(name = "time")
	private String time;

	@Ignore
	public Event(){

	}

	public Event(String name,String place,String category,String description,long date,String time,String creatorId,String image)
	{
		this.name = name;
		this.place = place;
		this.category = category;
		this.description = description;
		this.date = date;
		this.time = time;
		this.creatorId = creatorId;
		this.imagePath = image;

	}


	public String getName() {
		return name;
	}

	public String getPlace() { return place; }

	public String getCategory() { return category; }

	public String getDescription() { return description;	}

	public String getCreatorId() {	return creatorId;}

	public String getImagePath() {return imagePath;}

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
		result.put("imagePath",imagePath);
		result.put("time",time);
		return result;
	}
}

