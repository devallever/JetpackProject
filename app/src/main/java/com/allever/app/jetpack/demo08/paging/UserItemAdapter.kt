package com.allever.app.jetpack.demo08.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.allever.app.jetpack.R
import kotlinx.android.synthetic.main.rv_user_item.view.*




class UserItemAdapter :
    PagingDataAdapter<UserItem, UserItemAdapter.UserItemViewHolder>(DIFF_CALLBACK) {


    class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ivHead: ImageView = itemView.findViewById(R.id.ivHead)
        private var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.rv_user_item, parent, false)
        return UserItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
//        getItem(position)?.head?.let { holder.itemView.ivHead.setImageResource(it) }
        getItem(position)?.userName?.let {
            holder.itemView.tvUserName.text = it

        }
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
}