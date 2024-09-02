package com.example.roompractice3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Person_table")
data class Person (
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    val name : String,
    val age : Int
)