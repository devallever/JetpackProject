package com.allever.app.jetpack.demo05.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val userName: MutableLiveData<String> = MutableLiveData()
    val age: MutableLiveData<String> = MutableLiveData()
    val header: MutableLiveData<String> = MutableLiveData()


    fun initTestData() {
        userName.value = "Allever"
        age.value = "12"
        header.value = "https://www.baidu.com/img/dong_e70247ce4b0a3e5ba73e8b3b05429d84.gif"
    }


}