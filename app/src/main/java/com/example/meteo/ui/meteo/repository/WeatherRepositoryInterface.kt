package com.example.meteo.ui.meteo.repository

import com.example.meteo.ui.meteo.Resource
import com.example.meteo.ui.meteo.domain.WeatherResultDomain
import kotlinx.coroutines.flow.Flow

interface WeatherRepositoryInterface {
    suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>>
    suspend fun fetchWeather(lat: Double, lon: Double): Flow<Resource<WeatherResultDomain?>>
}