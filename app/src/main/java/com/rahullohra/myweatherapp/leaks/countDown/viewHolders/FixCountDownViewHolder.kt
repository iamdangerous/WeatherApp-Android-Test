package com.rahullohra.myweatherapp.leaks.countDown.viewHolders

import android.os.CountDownTimer
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class FixCountDownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var timer: CountDownTimer? = null
    fun setData() {

        timer = object : CountDownTimer(100L, 100L) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
    }

    //custom method
    fun onViewIsDetached(){
        clearData()
    }

    //custom method
    fun onActivityIsDestroyed(){
        clearData()
    }
    fun clearData() {
        timer?.cancel()
    }
}