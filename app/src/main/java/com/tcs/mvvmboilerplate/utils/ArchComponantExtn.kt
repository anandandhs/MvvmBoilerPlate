package com.tcs.mvvmboilerplate.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observeAndHandle(liveData: LiveData<T>,action:(t:T)->Unit){
    liveData.observe(this) { it?.let { t -> action(t) } }
}

fun <T> LifecycleOwner.observe(liveData: LiveData<T>){
    liveData.observe(this) {}
}