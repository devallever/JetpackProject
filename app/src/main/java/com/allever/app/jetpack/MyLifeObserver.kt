package com.allever.app.jetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.allever.app.jetpack.ext.logd

class MyLifeObserver(val lifecycle: Lifecycle) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onActivityStart() {
        logd("onActivityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onActivityStop() {
        logd("onActivityStop")
    }
}