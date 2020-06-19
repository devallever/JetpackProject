package com.allever.app.jetpack

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MainViewModel(count: Int) : ViewModel() {

    private val userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.firstName} ${it.lastName}"
    }

//    val userName2: LiveData<String> = Transformations.map(userLiveData, Function{
//        "${it.firstName} ${it.lastName}"
//    })

    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = count
    }

    fun plusOne() {
        val count = counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }
}