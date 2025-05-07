package com.example.percobaanmodul6;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {ContactModel.class}, version = 1)
public abstract class ContactDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();

    private static ContactDatabase instance;

    public static synchronized ContactDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ContactDatabase.class, "contact_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
