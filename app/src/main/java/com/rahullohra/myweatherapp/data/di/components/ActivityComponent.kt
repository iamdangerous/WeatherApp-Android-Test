package com.rahullohra.myweatherapp.data.di.components

import com.rahullohra.myweatherapp.data.di.modules.DispatcherModule
import com.rahullohra.myweatherapp.data.di.modules.ViewModelModule
import com.rahullohra.myweatherapp.data.di.scopes.ActivityScope
import com.rahullohra.myweatherapp.presentation.ui.activity.MainActivity
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ViewModelModule::class, DispatcherModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)
}