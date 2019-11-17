package com.rahullohra.myweatherapp.idlingResources

object IdlingResourceProvider {

    var resource: WeatherIdlingResource? = null
    fun provideIdlingResource(name: String): WeatherIdlingResource? {
        if (resource == null)
            resource = WeatherIdlingResource(name)
        return resource
    }
}