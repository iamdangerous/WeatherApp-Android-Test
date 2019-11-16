package com.rahullohra.myweatherapp.extras

object ApiEndpoints{
    const val BASE_URL = "https://api.openweathermap.org/"
    const val API_KEY = "33b2b51ba5d43f666a1e12d7abe19865"
    const val DEFAULT_DAYS = "4"
    const val METRIC = "metric"

    const val FORECAST_ENDPOINT = "/data/2.5/forecast"
    const val DAILY_FORECAST_ENDPOINT = FORECAST_ENDPOINT + "/daily"

    const val CACHE_HEADER = "Cache-Control: no-cache"
}