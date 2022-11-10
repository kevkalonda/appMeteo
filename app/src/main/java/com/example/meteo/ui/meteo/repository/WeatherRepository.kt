package com.example.meteo.ui.meteo.repository

import com.example.meteo.ui.meteo.OpenWeatherApi
import com.example.meteo.ui.meteo.Resource
import com.example.meteo.ui.meteo.domain.WeatherResultDomain
import com.example.meteo.ui.meteo.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent

class WeatherRepository(private val openWeatherApi: OpenWeatherApi) : KoinComponent,
    WeatherRepositoryInterface {

    override suspend fun fetchWeather(
        lat: Double,
        lon: Double
    ): Flow<Resource<WeatherResultDomain?>> {
        return fetch(null, lat, lon)
    }

    override suspend fun fetchWeather(city: String): Flow<Resource<WeatherResultDomain?>> {
        return fetch(city, null, null)
    }

    private suspend fun fetch(
        city: String?,
        lat: Double?,
        lon: Double?
    ): Flow<Resource<WeatherResultDomain?>> = flow {
        emit(Resource.Loading())
        if (city != null) {
            emit(Resource.Success(openWeatherApi.fetchWeather(city).toDomain()))
        } else emit(Resource.Success(openWeatherApi.fetchWeather(lat!!, lon!!).toDomain()))
    }
}