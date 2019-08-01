package com.example.forcastapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.forcastapplication.data.db.CurrentWeatherDao
import com.example.forcastapplication.data.db.unitlocalized.unitSpecificCurrentWeatherEntry
import com.example.forcastapplication.data.network.WeatherNetworkDataSource
import com.example.forcastapplication.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime
import java.util.*

class ForcastRepositoryImpl(
    private val currentWeatherDao:CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForcastRepository {

    init {
        weatherNetworkDataSource.downloadedCurrentWeather.observeForever { newCurrentWeather ->
            persisFetchedCurrentWeaether(newCurrentWeather)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out unitSpecificCurrentWeatherEntry> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    private fun persisFetchedCurrentWeaether(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntry)
        }
    }

    private suspend fun initWeatherData() {
        if(isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather(){
        weatherNetworkDataSource.fetchCurrentWeather("Los Angeles" , Locale.getDefault().language)
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirthMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirthMinutesAgo)
    }
}