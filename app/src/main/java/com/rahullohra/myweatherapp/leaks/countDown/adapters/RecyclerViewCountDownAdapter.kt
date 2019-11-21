package com.rahullohra.myweatherapp.leaks.countDown.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.myweatherapp.leaks.countDown.viewHolders.FixCountDownViewHolder
/*
*
* FIX
* 1. clear timer on activity is destroyed/ view is detached
* 2. Or Make your adapter/view holder lifecycle aware
* */

class RecyclerViewCountDownAdapter : RecyclerView.Adapter<FixCountDownViewHolder>() {
    var recyclerView:RecyclerView?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= FixCountDownViewHolder(View(parent.context))
    override fun getItemCount() = 0

    override fun onBindViewHolder(holder: FixCountDownViewHolder, position: Int) {
       holder.setData()
    }

    override fun onViewDetachedFromWindow(holder: FixCountDownViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.clearData()
    }

    override fun onViewAttachedToWindow(holder: FixCountDownViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.clearData()
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    //ensure it is called from activity's onDestroy
    fun onActivityDestroy(){
        //pass index for all items ==> too expensive
        //pass index for all the visible items ==> get visible item index from layout manager
        recyclerView?.findViewHolderForAdapterPosition(0)
    }

}