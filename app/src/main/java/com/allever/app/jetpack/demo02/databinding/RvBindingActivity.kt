package com.allever.app.jetpack.demo02.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allever.app.jetpack.R
import kotlinx.android.synthetic.main.activity_rv_binding.*

class RvBindingActivity: AppCompatActivity() {
    private var index = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_binding)

        val userList = mutableListOf<UserItem>()
        val adapter = UserAdapter(R.layout.item_rv_user, userList)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = adapter
        btnAdd.setOnClickListener {
            val userItem = UserItem()
            userItem.id = index.toString()
            userItem.name = "张$index"
            userItem.email = "$index@qq.com"
            userList.add(userItem)
            adapter.notifyDataSetChanged()
            index++
        }
    }
}