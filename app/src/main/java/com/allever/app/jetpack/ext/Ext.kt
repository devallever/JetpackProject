package com.allever.app.jetpack.ext

import android.util.Log

private const val TAG = "ILogger"

fun logd(content: String?) {
    content?:return
    Log.d(TAG, content)
}

fun loge(content: String?) {
    content?:return
    Log.d(TAG, content)
}

fun logTid() {
    android.os.Process.myTid()
}

fun logPid() {
    android.os.Process.myPid()
}

fun getTid(): Int = android.os.Process.myTid()
fun getPid(): Int = android.os.Process.myPid()
