package com.example.forcastapplication.ui.weather.current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.forcastapplication.R
import com.example.forcastapplication.data.ApixuWeatherApiService
import com.example.forcastapplication.data.network.ConnectivityInterceptorImpl
import com.example.forcastapplication.data.network.WeatherNetworkDataSource
import com.example.forcastapplication.data.network.WeatherNetworkDataSourceImpl
import com.example.forcastapplication.internal.glide.GlideApp
import com.example.forcastapplication.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class CurrentWeatherFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: CurrentWeatherViewModelFactory by instance()


    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)
        bindUI()

//        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.context!!))
//        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
//
//        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
//            textView.text = it.toString()
//        })
//
//        // GlobalScope is used for background task
//        GlobalScope.launch(Dispatchers.Main) {
//            weatherNetworkDataSource.fetchCurrentWeather("London","en")
//        }

    }

    private fun bindUI()= launch{
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(this@CurrentWeatherFragment, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.GONE
            updateLocation("Los Angles")
            updateDateToToday()
            updateTemperatures(it.temperature,it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection,it.windSpeed)
            updateVisibility(it.visibilityDistance)
//            http: -> //cdn.apixu.com/weather/64x64/day/116.png"
            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_condition_icon)
        })
    }

    private fun chooseLocalizationUnitAbbreviation(metric: String , imperial: String): String{
        return if(viewModel.isMateric) metric else imperial
    }

    private fun updateLocation(location: String){
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    private fun updateDateToToday(){
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperatures(temperature: Double , feelsLike: Double){
        val unitAbbreviation = chooseLocalizationUnitAbbreviation("°C" , "°F")
        textView_temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String){
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double){
        val uniAbbreviation = chooseLocalizationUnitAbbreviation("mm" , "in")
        textView_precipitation.text = "Precipitation: $precipitationVolume $uniAbbreviation"
    }

    private fun updateWind(windDirection: String , windSpeed: Double){
        val uniAbbreviation = chooseLocalizationUnitAbbreviation("kph" , "mph")
        textView_wind.text = "Wind: $windDirection , $windSpeed $uniAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double){
        val uniAbbreviation = chooseLocalizationUnitAbbreviation("km" , "mi")
        textView_visibility.text = "Visibility: $visibilityDistance $uniAbbreviation"
    }



}
