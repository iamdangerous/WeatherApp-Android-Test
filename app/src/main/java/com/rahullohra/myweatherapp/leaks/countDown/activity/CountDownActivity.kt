package com.rahullohra.myweatherapp.leaks.countDown.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

class CountDownActivity :Activity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutManager = LinearLayoutManager(this)
        val firstVisibleItemIndex = layoutManager.findFirstVisibleItemPosition()
        val lastVisibleItemIndex = layoutManager.findLastVisibleItemPosition()

    }

}