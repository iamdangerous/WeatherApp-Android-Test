package com.rahullohra.myweatherapp.domain.usecase

import android.content.Context
import com.rahullohra.myweatherapp.R
import com.rahullohra.myweatherapp.data.model.CurrentWeatherData
import com.rahullohra.myweatherapp.data.model.WeatherData
import com.rahullohra.myweatherapp.data.model.WeatherForecast
import com.rahullohra.myweatherapp.data.model.WeatherResponse
import com.rahullohra.myweatherapp.domain.repository.WeatherRepository
import java.text.MessageFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class GetWeatherUseCase @Inject constructor(
    val context: Context,
    val weatherRepository: WeatherRepository
) {

    suspend fun getWeather(location: String): Pair<CurrentWeatherData, List<WeatherData>> {
        val response = weatherRepository.getWeather(location)
        return responseToWeatherData(response)
    }

    private fun responseToWeatherData(response: WeatherResponse): Pair<CurrentWeatherData, List<WeatherData>> {
        val weatherForecast = response.listOfWeatherForecast.first()
        val currentWeatherData = CurrentWeatherData(
            getFormattedTemp(weatherForecast.main.temp.roundToInt().toString()),
            response.city.name
        )
        val list = splitWeatherForecastByDaysAndPickFirst(response.listOfWeatherForecast)
        return Pair(currentWeatherData, list.subList(1, list.size))
    }

    private fun splitWeatherForecastByDaysAndPickFirst(list: ArrayList<WeatherForecast>): List<WeatherData> {
        val daysList = ArrayList<String>()
        val weatherDataList = arrayListOf<WeatherData>()

        list.forEach {
            val day = epochToDay(it.dt * 1000L)
            if (daysList.isEmpty() || daysList.first() != day) {
                val temp = it.main.temp.toString()
                weatherDataList.add(
                    WeatherData(
                        day,
                        temp = getFormattedTemp(temp, R.string.cent_text)
                    )
                )
                daysList.add(0, day)
            }
        }
        return weatherDataList
    }

    private fun epochToDay(millis: Long): String {
        val c = Calendar.getInstance()
        c.timeZone = TimeZone.getTimeZone("GMT")
        c.time = Date(millis)
        return dayInIntToFormattedDay(c[Calendar.DAY_OF_WEEK])
    }

    private fun dayInIntToFormattedDay(dayIndex: Int): String {
        return DAY_ARRAY[dayIndex - 1]
    }

    private fun getFormattedTemp(temp: String, resId: Int = R.string.degree_text): String {
        return MessageFormat.format(context.getString(resId), temp)
    }

    companion object {
        val DAY_ARRAY = arrayListOf<String>(
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Staurday",
            "Sunday"
        )
    }

}