package com.rahullohra.myweatherapp.data.di.components

import com.rahullohra.myweatherapp.data.di.modules.*
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.presentation.ui.activity.MainActivity
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
interface ActivityComponent {

    fun inject(activity: MainActivity)
}