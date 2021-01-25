package com.allever.app.jetpack.demo05.databinding

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

class MyCustomView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}