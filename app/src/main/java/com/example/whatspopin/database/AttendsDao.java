package com.example.whatspopin.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttendsDao {

	@Query("SELECT * FROM Attends")
	List<Attends> getAttends();
	@Query("SELECT * FROM ATTENDS,USER,EVENT WHERE ATTENDS.eventId = USER.userId AND ATTENDS.eventId = EVENT.eventId")
	List<Event> test();
	@Insert
	void insertAttends(Attends attends);
	@Update
	void updateAttends(Attends attends);
	@Delete
	void deleteAttends(Attends attends);



}

