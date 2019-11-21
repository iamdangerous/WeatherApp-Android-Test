package com.rahullohra.myweatherapp.leaks.countDown.adapters

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.myweatherapp.leaks.countDown.viewHolders.FixCountDownViewHolder

class LifecycleAwareAdapter : RecyclerView.Adapter<FixCountDownViewHolder>(), LifecycleObserver {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FixCountDownViewHolder(View(parent.context))

    override fun getItemCount() = 0
    override fun onBindViewHolder(holder: FixCountDownViewHolder, position: Int) {}


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {

    }
}
