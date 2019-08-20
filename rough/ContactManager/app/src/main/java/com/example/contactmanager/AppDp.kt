package com.example.contactmanager

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [Mobile::class],version = 3)
abstract class AppDp : RoomDatabase(){
    abstract fun mobDao(): MobileDao
}