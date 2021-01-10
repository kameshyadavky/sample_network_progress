package com.beetlestance.sample.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.postValueIfNew(newValue: T) {
    if (this.value != newValue) postValue(newValue)
}