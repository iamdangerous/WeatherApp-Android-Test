package com.rahullohra.myweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("city") val city: City,
    @SerializedName("list") val listOfWeatherForecast: ArrayList<WeatherForecast>
)

data class WeatherForecast(
    @SerializedName("main") val main:Main,
    @SerializedName("dt") val dt:Long,
    @SerializedName("dt_txt") val formattedDate:String

)

data class City(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("coord") val coord: Coord
)

data class Coord(

    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)

data class Main(

    @SerializedName("temp") val temp: Double,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val temp_min: Double,
    @SerializedName("temp_max") val temp_max: Double
)