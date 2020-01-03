package com.example.whatspopin.database;

import androidx.room.Embedded;
import androidx.room.Relation;

public class EventUsers {
	@Embedded public User user;

}
