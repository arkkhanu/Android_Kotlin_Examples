package com.example.forcastapplication.ui.weather.current

import androidx.lifecycle.ViewModel;
import com.example.forcastapplication.data.repository.ForcastRepository
import com.example.forcastapplication.internal.UnitSystem
import com.example.forcastapplication.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forcastRepository: ForcastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC //get from settings later

    val isMateric : Boolean
    get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forcastRepository.getCurrentWeather(isMateric)
    }

}
