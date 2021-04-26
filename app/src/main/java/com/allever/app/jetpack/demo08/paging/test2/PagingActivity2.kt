package com.allever.app.jetpack.demo08.paging.test2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.allever.app.jetpack.R
import com.allever.app.jetpack.demo08.paging.UserItem
import com.allever.app.jetpack.ext.log
import kotlinx.android.synthetic.main.activity_main_paging.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PagingActivity2 : AppCompatActivity() {

    private val viewModel by viewModels<UserViewModel>()

    private val mJob = Job()
    private val mMainCoroutine = CoroutineScope(Dispatchers.Main + mJob)
    private lateinit var adapter: UserItemAdapter2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paging)


        mMainCoroutine.launch {
            adapter = UserItemAdapter2()
            rvUser.layoutManager = LinearLayoutManager(this@PagingActivity2)
            rvUser.adapter = adapter

            adapter.addLoadStateListener {
                if (it.refresh == LoadState.Loading) {
                    log("正在加载")
                } else {
                    log("不在正在加载")
                }
            }

            viewModel.userList.observe(this@PagingActivity2,
                Observer<PagingData<UserItem>> { t ->
                    mMainCoroutine.launch {
                        adapter.submitData(t!!)
                    }
                })
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }
}