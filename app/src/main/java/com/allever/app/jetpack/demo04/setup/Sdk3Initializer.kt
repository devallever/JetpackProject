package com.allever.app.jetpack.demo04.setup

import android.content.Context
import androidx.startup.Initializer
import com.allever.app.jetpack.ext.getPid
import com.allever.app.jetpack.ext.getTid
import com.allever.app.jetpack.ext.log

class Sdk3Initializer: Initializer<Sdk3> {
    private val TAG = javaClass.simpleName
    override fun create(context: Context): Sdk3 {
        log("onCreate ${this.javaClass.simpleName}")
        log("$TAG PID = ${getPid()}")
        log("$TAG TID = ${getTid()}")
        return Sdk3.getIns()
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        val list = mutableListOf<Class<out Initializer<*>>>()
        list.add(Sdk2Initializer::class.java)
        return list
    }
}