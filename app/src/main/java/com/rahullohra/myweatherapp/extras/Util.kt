package com.rahullohra.myweatherapp.extras

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.rahullohra.myweatherapp.R

object Util {

    fun getWeek(context: Context, day: Int): String {
        return when (day) {
            0 -> context.getString(R.string.sunday)
            1 -> context.getString(R.string.monday)
            2 -> context.getString(R.string.tuesday)
            3 -> context.getString(R.string.wednesday)
            4 -> context.getString(R.string.thursday)
            5 -> context.getString(R.string.friday)
            else ->
                context.getString(R.string.saturday)
        }
    }

    fun deviceDimensions(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }

}