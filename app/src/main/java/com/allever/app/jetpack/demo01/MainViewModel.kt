package com.allever.app.jetpack.demo01

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(): ViewModel() {

    val userLiveData = MutableLiveData<User>()

    private val mUser = User()

    constructor(id: Int) :this() {
        mUser.id = id
    }

    init { }

    fun updateUser(age: Int) {
        mUser.age = age
        userLiveData.postValue(mUser)
//        userLiveData.value = mUser
    }
}