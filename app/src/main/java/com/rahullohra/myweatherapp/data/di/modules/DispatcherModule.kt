package com.rahullohra.myweatherapp.data.di.modules

import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@AppScope
class DispatcherModule {

    @Provides
    @AppScope
    @Named(WORKER)
    fun provideWorkerDispatcher():CoroutineDispatcher = Dispatchers.IO

    @Provides
    @AppScope
    @Named(UI)
    fun provideMainDispatcher():CoroutineDispatcher = Dispatchers.Main

    companion object{
        const val WORKER = "WORKER"
        const val UI = "UI"
    }
}