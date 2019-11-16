package com.rahullohra.myweatherapp.data.di.modules

import android.content.Context
import android.location.Geocoder
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@Module
class LocationModule {

    @Provides
    @AppScope
    fun provideLocationModule(context: Context): Geocoder {
        return Geocoder(context)
    }

}