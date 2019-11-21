package com.rahullohra.myweatherapp.leaks.rx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RxPresenter :LifecycleObserver{

    val disposables = ArrayList<Disposable>()
    val compositeDisposable = CompositeDisposable()
    fun getData() {
        val obs = Observable.just(1, 2, 3, 4)

        val d = obs.subscribeOn(Schedulers.io())
            .subscribe()

        disposables.add(d)
        compositeDisposable.add(d)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun clearData(){
        disposables.forEach {
            it.dispose()
        }
        compositeDisposable.dispose()
        compositeDisposable.clear()
    }
}