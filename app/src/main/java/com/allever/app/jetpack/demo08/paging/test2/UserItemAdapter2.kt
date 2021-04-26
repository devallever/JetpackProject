package com.allever.app.jetpack.demo08.paging.test2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.allever.app.jetpack.R
import com.allever.app.jetpack.demo08.paging.UserItem

class UserItemAdapter2: PagingDataAdapter<UserItem,UserItemAdapter2.VH >(DIFF_CALLBACK) {
    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvUserName)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.userName == newItem.userName
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.userName == newItem.userName
            }

        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.tvTitle.text = getItem(position)?.userName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.rv_user_item, parent, false)
        return VH(root)
    }

}