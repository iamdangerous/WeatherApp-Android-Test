package com.rahullohra.myweatherapp.leaks

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import java.util.*

class LifecycleAwarePresenter: LifecycleObserver {


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){

    }
}