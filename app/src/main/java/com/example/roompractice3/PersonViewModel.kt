package com.example.roompractice3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PersonViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PersonRepository
    val allPersons: LiveData<List<Person>>

    init {
        val personDao = PersonDatabase.getDatabase(application).personDao()
        repository = PersonRepository(personDao)
        allPersons = repository.allPerson
    }

    fun insert(person: Person) = viewModelScope.launch {
        repository.insert(person)
    }

    fun update(person: Person) = viewModelScope.launch {
        repository.update(person)
    }

    fun delete(person: Person) = viewModelScope.launch {
        repository.delete(person)
    }

}