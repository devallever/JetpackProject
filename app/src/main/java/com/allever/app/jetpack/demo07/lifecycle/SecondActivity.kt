package com.allever.app.jetpack.demo07.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.allever.app.jetpack.demo07.lifecycle.lib.LiveDataBus

class SecondActivity : AppCompatActivity() {
    val stringLiveData = MutableLiveData<String>()

    val intLiveData = MutableLiveData<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveDataBus.getIns().setLiveData("1", stringLiveData)
        LiveDataBus.getIns().setLiveData("2", intLiveData)

    }

    override fun onDestroy() {
        super.onDestroy()
        stringLiveData.value = "Hello stringLiveData"
        intLiveData.value = 10000
    }
}