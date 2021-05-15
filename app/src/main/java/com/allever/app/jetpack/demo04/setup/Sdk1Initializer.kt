package com.allever.app.jetpack.demo04.setup

import android.content.Context
import androidx.startup.Initializer
import com.allever.app.jetpack.ext.getPid
import com.allever.app.jetpack.ext.getTid
import com.allever.app.jetpack.ext.log

class Sdk1Initializer: Initializer<Sdk1> {
    private val TAG = javaClass.simpleName
    override fun create(context: Context): Sdk1 {
        log("onCreate ${this.javaClass.simpleName}")
        log("$TAG PID = ${getPid()}")
        log("$TAG TID = ${getTid()}")
        return Sdk1.getIns(context)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}