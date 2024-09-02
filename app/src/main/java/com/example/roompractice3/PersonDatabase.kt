package com.example.roompractice3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Person::class], version = 1)
abstract class PersonDatabase : RoomDatabase() {
    abstract fun personDao() : PersonDao

    companion object{
        @Volatile
        private var INSTANCE : PersonDatabase? = null

        fun getDatabase(context: Context) : PersonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PersonDatabase::class.java,
                    "person_Database"
                ).build()

                INSTANCE =instance
                return instance
            }
        }
    }
}