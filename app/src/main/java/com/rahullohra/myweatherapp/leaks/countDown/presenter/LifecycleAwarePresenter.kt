package com.rahullohra.myweatherapp.leaks.countDown.presenter

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifecycleAwarePresenter : LifecycleObserver {



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){

    }
}