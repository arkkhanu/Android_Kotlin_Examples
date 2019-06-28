package com.example.roomdb.Database

import androidx.room.*

@Dao
interface NoteDao {

    @Query("select * from users")
    fun getAll() : List<Note>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

}