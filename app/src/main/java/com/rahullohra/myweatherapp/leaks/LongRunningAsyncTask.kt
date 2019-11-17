package com.rahullohra.myweatherapp.leaks

import android.os.AsyncTask
import android.util.Log

class LongRunningAsyncTask : AsyncTask<Any, Any, Any>() {
    val TAG = "LongRunningAsyncTask"

    override fun doInBackground(vararg params: Any?): Any {
        Log.d(TAG, "ASYNCTASK - I am going to sleep")
        Thread.sleep(15 * 1000L)
        Log.d(TAG, "ASYNCTASK - I woke up")
        return 1
    }
}