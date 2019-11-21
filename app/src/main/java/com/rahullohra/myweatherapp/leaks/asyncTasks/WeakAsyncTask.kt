package com.rahullohra.myweatherapp.leaks.asyncTasks

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.lang.ref.WeakReference

class WeakAsyncTask(weakContext: WeakReference<Context>) : AsyncTask<Any, Any, Any>() {
    val TAG = "WeakAsyncTask"

    override fun doInBackground(vararg params: Any?): Any {
        Log.d(TAG, "ASYNCTASK - I am going to sleep")
        Thread.sleep(15 * 1000L)
        Log.d(TAG, "ASYNCTASK - I woke up")
        return 1
    }
}
