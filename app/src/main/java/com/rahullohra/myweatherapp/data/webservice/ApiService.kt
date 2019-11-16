package com.rahullohra.myweatherapp.data.webservice

import com.rahullohra.myweatherapp.data.model.WeatherResponse
import com.rahullohra.myweatherapp.data.webservice.ApiParams.APP_ID
import com.rahullohra.myweatherapp.data.webservice.ApiParams.CNT
import com.rahullohra.myweatherapp.data.webservice.ApiParams.LAT
import com.rahullohra.myweatherapp.data.webservice.ApiParams.LOCATION
import com.rahullohra.myweatherapp.data.webservice.ApiParams.LON
import com.rahullohra.myweatherapp.data.webservice.ApiParams.TEMPERATURE_UNIT
import com.rahullohra.myweatherapp.extras.ApiEndpoints
import com.rahullohra.myweatherapp.extras.ApiEndpoints.CACHE_HEADER
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(CACHE_HEADER)
    @GET(ApiEndpoints.FORECAST_ENDPOINT)
    suspend fun getWeatherNormal(
        @Query(LOCATION) location: String = "paris",
        @Query(APP_ID) key: String = ApiEndpoints.API_KEY,
        @Query(TEMPERATURE_UNIT) temperatureUnit: String = ApiEndpoints.METRIC
    ): WeatherResponse

    @Headers(CACHE_HEADER)
    @GET(ApiEndpoints.DAILY_FORECAST_ENDPOINT)
    suspend fun getWeatherDaily(
        @Query(LAT) lat: String,
        @Query(LON) lon: String,
        @Query(APP_ID) key: String = ApiEndpoints.API_KEY,
        @Query(CNT) cnt: String = ApiEndpoints.DEFAULT_DAYS,
        @Query(TEMPERATURE_UNIT) temperatureUnit: String = ApiEndpoints.METRIC
    ): WeatherResponse
}

object ApiParams {

    const val APP_ID = "APPID"
    const val CNT = "cnt"
    const val LOCATION = "q"
    const val LAT = "lat"
    const val LON = "lon"
    const val TEMPERATURE_UNIT = "units"

}