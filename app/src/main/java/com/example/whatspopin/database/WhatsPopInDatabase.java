package com.example.whatspopin.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Event.class,User.class,Attends.class},exportSchema = false, version = 1)
public abstract class WhatsPopInDatabase extends RoomDatabase {
	private static final String DB_NAME = "whatspopinDatabase";
	private static WhatsPopInDatabase instance;

	public static synchronized WhatsPopInDatabase getInstance(Context context){
		if(instance == null){
			instance = Room.databaseBuilder(context.getApplicationContext(),WhatsPopInDatabase.class,DB_NAME)
					//.allowMainThreadQueries()//Gia debug mono prepei na to kanoume delete gia production
					.fallbackToDestructiveMigrationFrom().build();
		}
		return instance;
	}
	public abstract EventDao eventDao();
	public abstract UserDao userDao();
	public abstract AttendsDao attendsDao();


}

