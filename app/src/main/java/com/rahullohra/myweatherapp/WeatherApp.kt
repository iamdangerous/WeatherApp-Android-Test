package com.rahullohra.myweatherapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
import com.google.firebase.perf.FirebasePerformance
import com.rahullohra.myweatherapp.data.di.components.AppComponent
import com.rahullohra.myweatherapp.data.di.components.DaggerAppComponent
import com.rahullohra.myweatherapp.data.di.modules.AppModule

class WeatherApp : MultiDexApplication() {
    lateinit var appComponent:AppComponent
    companion object {
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        Dummy()
        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this)
        injectComponents()
        addPerformence()

    }

    fun addPerformence(){
        FirebasePerformance.getInstance()
            .newTrace("Hello")
    }

    private fun injectComponents(){
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
        appComponent.inject(this)
    }

}
