package com.allever.app.jetpack.widget.recycler

interface MultiItemTypeSupport<T> {
    fun getLayoutId(itemType: Int): Int
    fun getItemViewType(position: Int, t: T): Int
}