package com.rahullohra.myweatherapp

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.facebook.stetho.Stetho
//import com.rahullohra.fakecore.CoreDependency

class WeatherApp : MultiDexApplication() {
    companion object {
        lateinit var INSTANCE: Application
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Stetho.initializeWithDefaults(this);
        setFakeData()
    }

    private fun setFakeData(){
//        val fakeDependency = CoreDependency
//        fakeDependency.setupClient()
//
//        val f = fun(s: String): Boolean {
//            return false
//        }
    }
}
