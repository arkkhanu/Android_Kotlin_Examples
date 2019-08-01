package com.example.forcastapplication.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.forcastapplication.data.repository.ForcastRepository

class CurrentWeatherViewModelFactory(
    private val forcastRepository: ForcastRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forcastRepository) as T
    }
}