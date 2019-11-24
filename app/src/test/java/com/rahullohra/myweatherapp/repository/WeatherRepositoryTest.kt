package com.rahullohra.myweatherapp.repository

import com.rahullohra.myweatherapp.data.webservice.ApiService
import com.rahullohra.myweatherapp.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class WeatherRepositoryTest {

    val apiService: ApiService = mockk()
    lateinit var repository: WeatherRepository
    @Before
    fun setup() {
        repository = WeatherRepository(apiService)
    }

    @Test
    fun testRepository() {
        runBlockingTest {
            coEvery {
                apiService.getWeatherNormal(
                    any(),
                    any(),
                    any()
                )
            } returns mockk()
            repository.getWeather("Paris")
            coVerify(exactly = 1) { apiService.getWeatherNormal(any(), any(), any()) }
        }

    }


}