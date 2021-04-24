package com.allever.app.jetpack.demo08.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.allever.app.jetpack.R
import kotlinx.android.synthetic.main.rv_user_item.view.*

class UserItemAdapter(val context: Context, val data: MutableList<UserItem> ) : RecyclerView.Adapter<UserItemAdapter.UserItemViewHolder>() {


    class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ivHead: ImageView = itemView.findViewById(R.id.ivHead)
        private var tvUserName: TextView = itemView.findViewById(R.id.tvUserName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_user_item, parent, false)
        return UserItemViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.itemView.ivHead.setImageResource(data[position].head)
        holder.itemView.tvUserName.text = data[position].userName
    }
}