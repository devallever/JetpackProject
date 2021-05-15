package com.allever.app.jetpack.demo02.databinding

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRvAdapter<VH: RecyclerView.ViewHolder>: RecyclerView.Adapter<VH>() {
    abstract fun getItemLayoutId(): Int
    abstract fun getBindingVariable() : Int
}