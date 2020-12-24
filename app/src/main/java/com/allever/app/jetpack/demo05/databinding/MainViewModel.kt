package com.allever.app.jetpack.demo05.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData()
    val age: MutableLiveData<String> = MutableLiveData()
    val header: MutableLiveData<Any> = MutableLiveData()

    fun initTestData() {
        userName.value = "Allever"
        age.value = "12"
    }


}