package com.rahullohra.myweatherapp

import android.content.Context
import com.rahullohra.myweatherapp.data.di.modules.NetworkModule
import com.rahullohra.myweatherapp.extras.NoInternetException
import dagger.Module
import okhttp3.OkHttpClient

@Module
class FakeNetworkModule : NetworkModule() {

    override fun getClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { _ ->
                throw NoInternetException()
            }
            .build()
    }
}