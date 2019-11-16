package com.rahullohra.myweatherapp.domain.repository

import com.rahullohra.myweatherapp.data.model.WeatherResponse
import com.rahullohra.myweatherapp.data.webservice.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getWeather(location:String): WeatherResponse {
        return apiService.getWeatherNormal(location = location)
    }
}