package com.example.whatspopin.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AttendsDao {

	@Query("SELECT * FROM Attends")
	List<Attends> getAttends();
	@Insert
	void insertAttends(Attends attends);
	@Update
	Void updateAttends(Attends attends);
	@Delete
	Void deleteAttends(Attends attends);


}
