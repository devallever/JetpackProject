package com.allever.app.jetpack

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

class MyLifeObserver(val lifecycle: Lifecycle) : LifecycleEventObserver {

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

    }

//    @OnLifecycleEvent(Lifecycle.Event.ON_START)
//    fun onActivityStart() {
//        log("onActivityStart")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
//    fun onActivityCreate() {
//        log("onActivityCreate")
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    fun onActivityStop() {
//        log("onActivityStop")
//    }
}