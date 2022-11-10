package com.example.meteo.ui.meteo.model

import android.location.Location
import androidx.lifecycle.ViewModel
import com.example.meteo.ui.meteo.Status
import com.example.meteo.ui.meteo.domain.WeatherCityDomain
import com.example.meteo.ui.meteo.domain.WeatherItemDomain
import com.example.meteo.ui.meteo.repository.WeatherRepositoryInterface
import kotlinx.coroutines.flow.flow

data class WeatherViewModelState(
    var city: WeatherCityDomain? = null,
    var first: WeatherItemDomain? = null,
    var items: List<WeatherItemDomain> = emptyList(),
    var isLoading: Boolean = false,
)
class WeatherViewModel(private val repository: WeatherRepositoryInterface) :
    ViewModel() {

    fun cityChanged(value: String) = fetchWeather(value)

    fun locationChanged(value: Location) = fetchWeather(value)

    private fun fetchWeather(location: Location) = fetchWithFlow(null, location)

    private fun fetchWeather(city: String) = fetchWithFlow(city)

    private fun fetchWithFlow(city: String? = null, location: Location? = null) = flow {
        val flow = if (city != null) repository.fetchWeather(city) else repository.fetchWeather(
            location!!.latitude,
            location!!.longitude
        )
        flow.collect {
            val state = WeatherViewModelState(
                city = it.data?.city,
                first = it.data?.items?.firstOrNull(),
                items = it.data?.items ?: listOf(),
                isLoading = it.status == Status.LOADING
            )
            emit(
                state
            )
        }
    }
}