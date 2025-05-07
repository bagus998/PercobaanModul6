package com.example.percobaanmodul6;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface ContactDao {
    @Insert
    void insert(ContactModel contact);

    @Query("SELECT * FROM contacts")
    List<ContactModel> getAllContacts();

    @Delete
    void delete(ContactModel contact);
}
