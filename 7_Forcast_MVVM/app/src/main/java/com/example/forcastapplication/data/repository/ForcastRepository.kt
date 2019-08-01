package com.example.forcastapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.forcastapplication.data.db.unitlocalized.unitSpecificCurrentWeatherEntry

interface ForcastRepository {
    suspend fun getCurrentWeather(metric:Boolean): LiveData<out unitSpecificCurrentWeatherEntry>

}