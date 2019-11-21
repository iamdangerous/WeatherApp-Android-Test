package com.rahullohra.myweatherapp.leaks

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.lang.ref.WeakReference

class LongRunningAsyncTask(context: Context) : AsyncTask<Any, Any, Any>() {
    val TAG = "LongRunningAsyncTask"

    override fun doInBackground(vararg params: Any?): Any {
        Log.d(TAG, "ASYNCTASK - I am going to sleep")
        Thread.sleep(15 * 1000L)
        Log.d(TAG, "ASYNCTASK - I woke up")
        return 1
    }
}


class WeakLongRunningAsyncTask(weakContext: WeakReference<Context>) : AsyncTask<Any, Any, Any>() {
    val TAG = "LongRunningAsyncTask"

    override fun doInBackground(vararg params: Any?): Any {
        Log.d(TAG, "ASYNCTASK - I am going to sleep")
        Thread.sleep(15 * 1000L)
        Log.d(TAG, "ASYNCTASK - I woke up")
        return 1
    }
}