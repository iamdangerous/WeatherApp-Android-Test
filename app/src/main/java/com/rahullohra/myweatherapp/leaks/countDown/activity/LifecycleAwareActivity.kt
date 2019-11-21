package com.rahullohra.myweatherapp.leaks.countDown.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rahullohra.myweatherapp.leaks.countDown.presenter.LifecycleAwarePresenter


// Lifecycle aware components

class LifecycleAwareActivity : AppCompatActivity() {

    val presenter = LifecycleAwarePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.addObserver(presenter)
    }

}