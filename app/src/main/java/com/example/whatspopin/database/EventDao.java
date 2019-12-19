package com.example.whatspopin.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface EventDao {
	@Query("Select * from Event")
	List<Event> getEventList();
	@Insert
	void insertEvent(Event event);
	@Update
	void updateEvent(Event event);
	@Delete
	void deleteEvent(Event event);

}
