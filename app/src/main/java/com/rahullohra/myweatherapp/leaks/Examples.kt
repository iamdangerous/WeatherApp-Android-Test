package com.rahullohra.myweatherapp.leaks

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference


/*
* 3. Leak via runnable in Java class
* class path - com.rahullohra.myweatherapp.leaks.LeakActivity
* */





