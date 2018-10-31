package com.example.phobos.roomtest;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM people")
    List<Person> getAll();

    @Query("SELECT * FROM people WHERE id = :id")
    Person getById(int id);

    @Insert
    long insertPerson(Person person);
}
