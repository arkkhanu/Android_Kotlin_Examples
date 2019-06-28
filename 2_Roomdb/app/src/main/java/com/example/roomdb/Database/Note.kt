package com.example.roomdb.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val note_id:Int,
    @ColumnInfo(name = "content")
    val content:String,
    @ColumnInfo(name = "title")
    val title:String
)
