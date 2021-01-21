package com.allever.app.jetpack

import android.app.Application
import android.content.Context
import com.allever.app.jetpack.ext.getPid
import com.allever.app.jetpack.ext.getTid
import com.allever.app.jetpack.ext.logd

class MyApp : Application() {
    private val TAG = javaClass.simpleName
    override fun onCreate() {
        super.onCreate()
        logd("onCreate $TAG")
        logd("$TAG PID = ${getPid()}")
        logd("$TAG TID = ${getTid()}")
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}