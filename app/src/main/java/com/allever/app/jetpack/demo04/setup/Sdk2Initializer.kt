package com.allever.app.jetpack.demo04.setup

import android.content.Context
import androidx.startup.Initializer
import com.allever.app.jetpack.ext.getPid
import com.allever.app.jetpack.ext.getTid
import com.allever.app.jetpack.ext.log

class Sdk2Initializer: Initializer<Sdk2> {
    private val TAG = javaClass.simpleName
    override fun create(context: Context): Sdk2 {
        log("onCreate ${this.javaClass.simpleName}")
        log("$TAG PID = ${getPid()}")
        log("$TAG TID = ${getTid()}")
        return Sdk2.getIns()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}