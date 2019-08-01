package com.example.forcastapplication.data.network.response

import com.example.forcastapplication.data.db.entity.CurrentWeatherEntry
import com.example.forcastapplication.data.db.entity.Location
import com.google.gson.annotations.SerializedName


data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)