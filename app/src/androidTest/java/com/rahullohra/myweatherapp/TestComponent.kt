package com.rahullohra.myweatherapp

import com.rahullohra.myweatherapp.data.di.modules.AppModule
import com.rahullohra.myweatherapp.data.di.modules.DispatcherModule
import com.rahullohra.myweatherapp.data.di.modules.LocationModule
import com.rahullohra.myweatherapp.data.di.modules.ViewModelModule
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Component

@AppScope
@Component(
    modules = [FakeNetworkModule::class,
        AppModule::class,
        ViewModelModule::class,
        DispatcherModule::class,
        LocationModule::class
    ]
)
interface TestComponent {

    fun inject(activityTest: WeatherAppUiTest)
}