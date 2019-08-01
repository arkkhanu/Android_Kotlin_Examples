package com.example.forcastapplication

import android.app.Application
import com.example.forcastapplication.data.ApixuWeatherApiService
import com.example.forcastapplication.data.db.ForcastDatabase
import com.example.forcastapplication.data.network.ConnectivityInterceptor
import com.example.forcastapplication.data.network.ConnectivityInterceptorImpl
import com.example.forcastapplication.data.network.WeatherNetworkDataSource
import com.example.forcastapplication.data.network.WeatherNetworkDataSourceImpl
import com.example.forcastapplication.data.repository.ForcastRepository
import com.example.forcastapplication.data.repository.ForcastRepositoryImpl
import com.example.forcastapplication.ui.weather.current.CurrentWeatherViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForcastApplication : Application(), KodeinAware {
    // dependency Injection
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForcastApplication))

        bind() from singleton { ForcastDatabase(instance()) }
        bind() from singleton { instance<ForcastDatabase>().currentWeatherDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForcastRepository>() with singleton { ForcastRepositoryImpl(instance(),instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }

}