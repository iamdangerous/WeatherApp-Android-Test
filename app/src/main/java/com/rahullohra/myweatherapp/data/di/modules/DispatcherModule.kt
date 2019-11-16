package com.rahullohra.myweatherapp.data.di.modules

import com.rahullohra.myweatherapp.data.di.scopes.ActivityScope
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
class DispatcherModule {

    @Provides
    @ActivityScope
    @Named(WORKER)
    fun provideWorkerDispatcher():CoroutineDispatcher = Dispatchers.IO

    @Provides
    @ActivityScope
    @Named(UI)
    fun provideMainDispatcher():CoroutineDispatcher = Dispatchers.Main

    companion object{
        const val WORKER = "WORKER"
        const val UI = "UI"
    }
}