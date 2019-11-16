package com.rahullohra.myweatherapp.di.modules

import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.data.webservice.ApiService
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import okhttp3.OkHttpClient

@Module
class FakeNetworkModule {

    @AppScope
    @Provides
    fun provideOkHttp(): OkHttpClient {
        return mockk()
    }

    @AppScope
    @Provides
    fun provideApiService(): ApiService {
        return mockk()
    }

}