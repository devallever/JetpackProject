package com.allever.app.jetpack.demo05.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.allever.app.jetpack.databinding.ItemUserBinding

class UserItemAdapter(val context: Context, private val layoutId: Int, val data: MutableList<UserItem>) : RecyclerView.Adapter<UserViewHolder>() {
    lateinit var binding: ItemUserBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, false)
        binding.executePendingBindings()
//        val itemView = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return UserViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.tvName.text = data[position].name
        holder.binding.tvIdentity.text = data[position].identity
//        holder.tvName.text = data[position].name
//        holder.tvIdentity.text = data[position].identity
    }
}