package com.rahullohra.myweatherapp.data.di.components

import android.content.Context
import android.location.Geocoder
import com.rahullohra.myweatherapp.WeatherApp
import com.rahullohra.myweatherapp.data.di.modules.*
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.data.webservice.ApiService
import dagger.Component

@AppScope
@Component(
    modules = [NetworkModule::class,
        AppModule::class,
        LocationModule::class
    ]
)
interface AppComponent {

    //expose items for otherComponents
    val context:Context
    val apiService:ApiService
    val geoCoder:Geocoder

    fun inject (app:WeatherApp)
}