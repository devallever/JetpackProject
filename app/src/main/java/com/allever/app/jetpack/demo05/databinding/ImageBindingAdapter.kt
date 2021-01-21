package com.allever.app.jetpack.demo05.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.allever.app.jetpack.MyApp
import com.bumptech.glide.Glide

@BindingAdapter("resId", "uri", requireAll = false)
fun loadImage(imageView: ImageView, resId: Int?, uri: String?) {
    if (resId != null) {
        Glide.with(MyApp.context).load(resId).into(imageView)
    }
    if (uri != null) {
        Glide.with(MyApp.context).load(uri).into(imageView)
    }

}