package com.rahullohra.myweatherapp.idlingResources


object IdlingProvider {

    var resource: WeatherIdlingResource? = null
    fun provideIdlingResource(name: String): WeatherIdlingResource? {
        if (resource == null)
            resource = WeatherIdlingResource(name)
        return resource
    }
}