package com.allever.app.jetpack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(count: Int) : ViewModel() {

    val counter = MutableLiveData<Int>()

    init {
        counter.value = count
    }

    fun plusOne() {
        val count = counter.value ?: 0
        counter.value = count + 1
    }

    fun clear() {
        counter.value = 0
    }
}