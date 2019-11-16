package com.rahullohra.myweatherapp.di.modules

import android.content.Context
import android.location.Geocoder
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import io.mockk.mockk

@Module
class FakeLocationModule {

    @Provides
    @AppScope
    fun provideLocationModule(context: Context): Geocoder {
        return mockk()
    }
}