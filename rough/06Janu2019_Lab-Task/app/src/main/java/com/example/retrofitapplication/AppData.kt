package com.example.retrofitapplication

import android.app.Application
import com.facebook.stetho.Stetho

class AppData:Application(){
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}