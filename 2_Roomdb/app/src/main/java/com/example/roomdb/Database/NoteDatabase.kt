package com.example.roomdb.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class) , version = 1 , exportSchema = false)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{
        private var mInstance: NoteDatabase? = null
        fun getInstance(context: Context ): NoteDatabase{
            if(mInstance == null)
            {
                synchronized(NoteDatabase::class)
                {
                    mInstance = Room.databaseBuilder(
                        context.applicationContext
                        ,NoteDatabase::class.java,"userDatabase")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return mInstance!!
        }
    }

    public fun clearUp(){
        mInstance
    }

}