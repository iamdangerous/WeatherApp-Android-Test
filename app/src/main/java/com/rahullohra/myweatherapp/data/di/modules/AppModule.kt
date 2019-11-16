package com.rahullohra.myweatherapp.data.di.modules

import android.content.Context
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Module
import dagger.Provides

@AppScope
@Module
class AppModule(val context: Context) {

    @Provides
    @AppScope
    fun provideContext() = context
}