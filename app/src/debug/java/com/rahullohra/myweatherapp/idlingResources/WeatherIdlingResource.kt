package com.rahullohra.myweatherapp.idlingResources

import androidx.test.espresso.IdlingResource
import java.util.concurrent.atomic.AtomicInteger

class WeatherIdlingResource(val mResourceName: String) : IdlingResource {
    private var resourceCallback: IdlingResource.ResourceCallback? = null
    val counter = AtomicInteger(0)

    override fun getName(): String {
        return mResourceName
    }

    override fun isIdleNow(): Boolean {
        return counter.get() == 0
    }

    fun increment() {
        counter.getAndIncrement()
    }

    fun decrement() {
        val counterVal = counter.decrementAndGet()
        if (counterVal == 0) {
            resourceCallback?.onTransitionToIdle()
        }

        if (counterVal < 0) {
            throw IllegalArgumentException("Counter has been corrupted!")
        }
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }
}