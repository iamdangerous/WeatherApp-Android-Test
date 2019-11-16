package com.rahullohra.myweatherapp.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahullohra.myweatherapp.data.LiveDataResult
import com.rahullohra.myweatherapp.data.di.modules.DispatcherModule.Companion.UI
import com.rahullohra.myweatherapp.data.di.modules.DispatcherModule.Companion.WORKER
import com.rahullohra.myweatherapp.data.model.CurrentWeatherData
import com.rahullohra.myweatherapp.data.model.DeviceLocation
import com.rahullohra.myweatherapp.data.model.WeatherData
import com.rahullohra.myweatherapp.domain.usecase.GetWeatherUseCase
import com.rahullohra.myweatherapp.domain.usecase.LocationUseCase
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class WeatherViewModel @Inject constructor(
    val weatherUseCae: GetWeatherUseCase,
    val locationUseCase: LocationUseCase,
    @Named(UI)
    val uiDispatcher: CoroutineDispatcher,
    @Named(WORKER)
    val workerDispatcher: CoroutineDispatcher
) : ViewModel(), CoroutineScope {

    val weatherLiveData =
        MutableLiveData<LiveDataResult<Pair<CurrentWeatherData, List<WeatherData>>>>()
    val locationLiveData = MutableLiveData<LiveDataResult<DeviceLocation>>()

    private val job = Job()
    private val ceh = CoroutineExceptionHandler { _, ex ->
        ex.printStackTrace()
        weatherLiveData.postValue(LiveDataResult.error(ex))
    }

    override val coroutineContext: CoroutineContext
        get() = job + workerDispatcher + ceh

    fun getWeather(deviceLocation: DeviceLocation) {
        launch {
            val response = weatherUseCae.getWeather(deviceLocation.name)
            weatherLiveData.postValue(LiveDataResult.success(response))
        }
    }

    fun getLocation(latitude: Double, longitude: Double) {
        launch(CoroutineExceptionHandler { _, ex ->
            locationLiveData.postValue(LiveDataResult.error(ex))
        }) {
            val location = locationUseCase.processAddress(latitude, longitude)
            locationLiveData.postValue(
                LiveDataResult.success(
                    DeviceLocation(
                        latitude,
                        longitude,
                        location
                    )
                )
            )
        }

    }

    override fun onCleared() {
        super.onCleared()
        if (job.isActive) {
            job.cancel()
        }
    }
}