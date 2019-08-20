package com.example.contactmanager

import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
            applicationContext,
            AppDp::class.java,
           "MobileDb"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

        val mob = Mobile()
        mob.mbID = 12345
        mob.isQw = true
        mob.mnFr = "Moto"
        mob.type = "Tab"

        db.mobDao().saveMobile(mob)
        db.mobDao().getAll().forEach {
            Log.i("@codekul", """ ID -  ${it.mbID} """ )
            Log.i("@codekul", """ Fr -  ${it.mnFr} """ )
            Log.i("@codekul", """ Type -  ${it.type} """ )
            Log.i("@codekul", """ Qwerty  -  ${it.isQw} """ )
        }
    }
}
