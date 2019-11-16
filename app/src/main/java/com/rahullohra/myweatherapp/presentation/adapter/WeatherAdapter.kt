package com.rahullohra.myweatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rahullohra.myweatherapp.R
import com.rahullohra.myweatherapp.data.model.WeatherData
import com.rahullohra.myweatherapp.presentation.ui.viewHolder.WeatherViewHolder

class WeatherAdapter(val list: List<WeatherData>) : RecyclerView.Adapter<WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_view_weather, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.setData(list[position])
    }
}
