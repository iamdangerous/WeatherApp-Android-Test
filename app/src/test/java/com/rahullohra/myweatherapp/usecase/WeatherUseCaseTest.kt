package com.rahullohra.myweatherapp.usecase

import android.content.Context
import com.google.gson.Gson
import com.rahullohra.myweatherapp.data.model.WeatherResponse
import com.rahullohra.myweatherapp.domain.repository.WeatherRepository
import com.rahullohra.myweatherapp.domain.usecase.GetWeatherUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.File

@RunWith(JUnit4::class)
class WeatherUseCaseTest {

    val context: Context = mockk()
    val weatherRepository: WeatherRepository = mockk()

    lateinit var usecase: GetWeatherUseCase

    @Before
    fun setup() {
        usecase = GetWeatherUseCase(context, weatherRepository)
    }

    @Test
    fun getWeatherSuccess() {
        val folderPath = "src/test/resources"
        val filePath = "json/weather_normal.json"
        val file = File(folderPath, filePath)
        val text = file.reader().readText()
        val weatherResponse: WeatherResponse =
            Gson().fromJson<WeatherResponse>(text, WeatherResponse::class.java)
        runBlockingTest {
            val location = "paris"
            coEvery { weatherRepository.getWeather(location) } returns weatherResponse
            every { context.getString(any()) } returns ""

            val data = usecase.getWeather(location)

            coVerify(exactly = 1) { weatherRepository.getWeather(location) }
            Assert.assertEquals(data.first.name.isNotEmpty(), true)
            Assert.assertEquals(data.second.isNotEmpty(), true)
        }
    }

}
