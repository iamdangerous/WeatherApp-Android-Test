package com.rahullohra.myweatherapp

import android.content.Context
import com.rahullohra.myweatherapp.data.di.modules.NetworkModule
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.extras.NoInternetException
import dagger.Provides
import okhttp3.OkHttpClient

class FakeNetworkModule : NetworkModule() {

    @Provides
    @AppScope
    override fun createOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { t ->
                throw NoInternetException()
            }
            .build()
    }
}