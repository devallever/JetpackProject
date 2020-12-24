package com.allever.app.jetpack.demo05.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.allever.app.jetpack.R
import com.allever.app.jetpack.databinding.ActivityMainDemo05Binding
import kotlinx.android.synthetic.main.activity_main_demo_05.*

class MainActivity : AppCompatActivity() {
    private val mViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val binding = ActivityMainDemo05Binding.inflate(layoutInflater)
        val binding = DataBindingUtil.setContentView<ActivityMainDemo05Binding>(this, R.layout.activity_main_demo_05)
        binding.lifecycleOwner = this
        binding.viewModel = mViewModel
        mViewModel.initTestData()
        initUserItemData()
    }

    private fun initUserItemData() {
        val userItemList = mutableListOf<UserItem>()
        for (i in 0..10) {
            val userItem = UserItem()
            userItem.name = "姓名: ${i+1}"
            userItem.identity = "身份证: ${i+1}"
            userItemList.add(userItem)
        }

        val adapter = UserItemAdapter(this, R.layout.item_user, userItemList)
        rvUser.layoutManager = LinearLayoutManager(this)
        rvUser.adapter = adapter
    }


}