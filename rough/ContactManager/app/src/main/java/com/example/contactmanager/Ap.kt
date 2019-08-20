package com.example.contactmanager

import android.app.Application
import com.facebook.stetho.Stetho

class Ap:Application(){

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}