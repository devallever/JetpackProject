package com.allever.app.jetpack.widget.recycler

import com.allever.app.jetpack.widget.recycler.BaseViewHolder

interface ItemListener {
    fun onItemClick(position: Int, holder: BaseViewHolder)
    fun onItemLongClick(position: Int, holder: BaseViewHolder): Boolean = false
}