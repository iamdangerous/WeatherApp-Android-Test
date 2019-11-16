package com.rahullohra.myweatherapp.data.di.modules

import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.rahullohra.myweatherapp.data.di.scopes.AppScope
import com.rahullohra.myweatherapp.extras.ApiEndpoints
import com.rahullohra.myweatherapp.data.webservice.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@AppScope
@Module
open class NetworkModule {

    @Provides
    @AppScope
    open fun createOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { t ->
                val original = t.request()
                val requestBuilder = original.newBuilder().header("Authorization", "Bearer " + "")
                t.proceed(requestBuilder.build())
            }
            .cache(provideCache(context))
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }


    @Provides
    @AppScope
    fun provideCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024L // 10 MB
        return Cache(context.cacheDir, cacheSize)
    }


    @Provides
    @AppScope
    inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
        val gson = GsonBuilder()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(T::class.java)
    }

    @Provides
    @AppScope
    fun provideApiService(okHttpClient: OkHttpClient): ApiService {
        return createWebService(okHttpClient, ApiEndpoints.BASE_URL)
    }
}