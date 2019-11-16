package com.rahullohra.myweatherapp.presentation.contract

import com.rahullohra.myweatherapp.data.model.CurrentWeatherData
import com.rahullohra.myweatherapp.data.model.WeatherData

interface MainActivityContract {

    fun toggleProgressBar(value: Boolean)
    fun onFail()
    fun showResult(pair: Pair<CurrentWeatherData, List<WeatherData>>)
}