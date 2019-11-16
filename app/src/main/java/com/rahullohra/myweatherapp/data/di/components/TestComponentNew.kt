package com.rahullohra.myweatherapp.data.di.components

import com.rahullohra.myweatherapp.WeatherApp
import com.rahullohra.myweatherapp.data.di.modules.*
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(
    modules = [NetworkModule::class,
        AppModule::class,
        ViewModelModule::class,
        DispatcherModule::class,
        LocationModule::class
    ]
)
interface TestComponentNew {

    fun inject(app: WeatherApp)
}