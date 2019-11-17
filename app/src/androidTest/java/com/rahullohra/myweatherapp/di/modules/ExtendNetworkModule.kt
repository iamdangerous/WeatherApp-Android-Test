package com.rahullohra.myweatherapp.di.modules

import android.content.Context
import com.rahullohra.myweatherapp.data.di.modules.NetworkModule
import com.rahullohra.myweatherapp.data.webservice.ApiService
import io.mockk.mockk
import okhttp3.OkHttpClient


class ExtendNetworkModule : NetworkModule(){

    override fun createOkHttpClient(context: Context): OkHttpClient {
        return mockk()
    }

    override fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return mockk()
    }
}