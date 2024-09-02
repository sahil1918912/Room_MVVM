package com.example.roompractice3

import androidx.lifecycle.LiveData

class PersonRepository(private val personDao: PersonDao) {
    val allPerson : LiveData<List<Person>> = personDao.getAllPersonData()

    suspend fun insert(person: Person) {
        personDao.insertPersonDetails(person)
    }

    suspend fun update(person: Person) {
        personDao.updatePersonDetails(person)
    }

    suspend fun delete(person: Person) {
        personDao.deletePersonDetails(person)
    }
}