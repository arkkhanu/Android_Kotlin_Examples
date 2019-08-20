package com.example.contactmanager

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Mobile {

    @PrimaryKey(autoGenerate = true) var mbID: Long? = null
    @ColumnInfo(name ="mob_type") var type: String? = null
    @ColumnInfo(name ="mob_mnfr") var mnFr: String? = null
    @ColumnInfo(name ="is_qwerty") var isQw: Boolean? = null


}