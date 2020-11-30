package com.allever.app.jetpack.demo02.databinding

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.allever.app.jetpack.BR
import com.bumptech.glide.Glide

class UserAdapter(private val layoutId: Int, private val data: MutableList<UserItem>) :
    RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
        )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.setVariable(BR.user, data[position])
        holder.binding.executePendingBindings()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl", "errorRes")
        fun loadHeader(imageView: ImageView, header: String, error: Drawable) {
            Glide.with(imageView.context).load(header).error(error).into(imageView)
        }
    }
}

