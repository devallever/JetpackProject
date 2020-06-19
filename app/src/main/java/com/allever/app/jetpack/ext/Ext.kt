package com.allever.app.jetpack.ext

import android.content.Context
import android.util.Log

private const val TAG = "ILogger"

fun logd(content: String?) {
    Log.d(TAG, content)
}