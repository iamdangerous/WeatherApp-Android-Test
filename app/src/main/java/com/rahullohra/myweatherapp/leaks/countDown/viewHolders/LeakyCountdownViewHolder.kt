package com.rahullohra.myweatherapp.leaks.countDown.viewHolders

import android.os.CountDownTimer
import android.view.View
import androidx.recyclerview.widget.RecyclerView

//CountDownTimer will hold the reference of MyViewHolder and will leak context
class LeakyCountdownViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    var timer: CountDownTimer? = null
    fun setData() {

        timer = object : CountDownTimer(100L, 100L) {
            override fun onFinish() {

            }

            override fun onTick(millisUntilFinished: Long) {

            }
        }
    }
}