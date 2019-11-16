package com.rahullohra.myweatherapp.di.components

import com.rahullohra.myweatherapp.WeatherAppUiTest
import com.rahullohra.myweatherapp.data.di.components.AppComponent
import com.rahullohra.myweatherapp.data.di.modules.AppModule
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.di.modules.FakeLocationModule
import com.rahullohra.myweatherapp.di.modules.FakeNetworkModule
import com.rahullohra.myweatherapp.di.scopes.DebugScope
import dagger.Component

@AppScope
@Component(
    modules = [FakeNetworkModule::class,
        AppModule::class,
        FakeLocationModule::class
    ]
)
interface TestComponent : AppComponent {

    fun inject(activityTest: WeatherAppUiTest)
}