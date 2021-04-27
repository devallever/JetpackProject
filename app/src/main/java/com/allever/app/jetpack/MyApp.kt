package com.allever.app.jetpack

import android.app.Application
import android.content.Context
import com.allever.app.jetpack.ext.getPid
import com.allever.app.jetpack.ext.getTid
import com.allever.app.jetpack.ext.log

class MyApp : Application() {
    private val TAG = javaClass.simpleName
    override fun onCreate() {
        super.onCreate()
        log("onCreate $TAG")
        log("$TAG PID = ${getPid()}")
        log("$TAG TID = ${getTid()}")
        context = this
    }

    companion object {
        lateinit var context: Context
    }
}