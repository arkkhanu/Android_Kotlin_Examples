package com.example.contactmanager

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface MobileDao{

    @Insert
    fun saveMobile(mob:Mobile)

    @Query("SELECT * FROM Mobile")
    fun getAll():List<Mobile>

}