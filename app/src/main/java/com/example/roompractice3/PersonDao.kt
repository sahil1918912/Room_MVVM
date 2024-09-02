package com.example.roompractice3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PersonDao {

    @Insert
    suspend fun insertPersonDetails(person: Person)

    @Update
    suspend fun updatePersonDetails(person: Person)

    @Delete
    suspend fun deletePersonDetails(person: Person)

    @Query("SELECT * FROM person_table ORDER BY id ASC")
    fun getAllPersonData() : LiveData<List<Person>>
}