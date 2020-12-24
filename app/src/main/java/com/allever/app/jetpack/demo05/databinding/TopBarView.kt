package com.allever.app.jetpack.demo05.databinding

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.allever.app.jetpack.R
import com.allever.app.jetpack.databinding.WidgetTopBarViewBinding

/**
 * @author allever
 */
class TopBarView : FrameLayout {
    private lateinit var binding: WidgetTopBarViewBinding
    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        inflateLayout()
    }

    private fun inflateLayout() {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.widget_top_bar_view, this, true)
//        LayoutInflater.from(context).inflate(R.layout.widget_top_bar_view, this, true)
    }


}