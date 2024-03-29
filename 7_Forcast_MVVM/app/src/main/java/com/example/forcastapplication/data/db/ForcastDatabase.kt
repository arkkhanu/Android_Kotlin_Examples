package com.example.forcastapplication.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forcastapplication.data.db.entity.CurrentWeatherEntry

@Database(entities = [CurrentWeatherEntry::class] , version = 1)
abstract class ForcastDatabase  : RoomDatabase(){
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object{
        @Volatile private var instance:ForcastDatabase ?= null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context:Context) =
            Room.databaseBuilder(context.applicationContext,ForcastDatabase::class.java,"forecase.db").build()
    }
}