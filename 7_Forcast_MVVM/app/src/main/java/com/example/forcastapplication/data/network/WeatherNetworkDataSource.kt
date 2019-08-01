package com.example.forcastapplication.data.network

import androidx.lifecycle.LiveData
import com.example.forcastapplication.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val downloadedCurrentWeather : LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String,languageCode: String)
}