package com.allever.app.jetpack.ext

import android.content.Context
import android.util.Log
import android.widget.Toast

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

fun Context.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
