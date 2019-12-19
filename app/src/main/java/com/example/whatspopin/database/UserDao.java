package com.example.whatspopin.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
	@Query("SELECT * FROM USER")
	List<User> getUserList();
	@Insert
	void insertUser(User user);
	@Update
	void updateUser(User user);
	@Delete
	void deleteUser(User user);
}
