package com.rahullohra.myweatherapp.presentation.ui.viewHolder

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.myweatherapp.R
import com.rahullohra.myweatherapp.data.model.WeatherData

class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val tvTitle = itemView.findViewById<AppCompatTextView>(R.id.tv_item_title)
    val tvTemp = itemView.findViewById<AppCompatTextView>(R.id.tv_item_temp)

    fun setData(data: WeatherData) {
        tvTitle.text = data.title
        tvTemp.text = data.temp
    }
}

