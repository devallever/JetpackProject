package com.allever.app.jetpack.demo08.paging

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.allever.app.jetpack.R
import com.allever.app.jetpack.ext.log
import kotlinx.android.synthetic.main.activity_main_paging.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.suspendCoroutine

class PagingActivity : AppCompatActivity() {

    private val mJob = Job()
    private val mMainCoroutine = CoroutineScope(Dispatchers.Main + mJob)

    private lateinit var adapter: UserItemAdapter

    private val mViewModel by viewModels<PageViewModel> ()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paging)



        log("主线程：${android.os.Process.myTid()}")
        mMainCoroutine.launch {

            val response = WanAnderoidApi.getService().getPageList(20)
            val result = response.data.datas
            val userList = mutableListOf<UserItem>()
            result.map {
                val userItem = UserItem(R.mipmap.ic_launcher, it.title)
                userList.add(userItem)
            }


            adapter = UserItemAdapter()
            adapter.addLoadStateListener {
                if (it.refresh == LoadState.Loading) {
                    log("show progress")
                    // show progress view
                } else {
                    //hide progress view
                    log("hide progress")
                }
            }
//            rvUser.adapter = adapter.withLoadStateHeaderAndFooter(
//                header = HeaderFooterAdapter(adapter),
//                footer = HeaderFooterAdapter(adapter)
//            )

            log("当前线程：${android.os.Process.myTid()}")
            val layoutManager = LinearLayoutManager(this@PagingActivity)
            rvUser.layoutManager = layoutManager
            rvUser.adapter = adapter


            mViewModel.listData.collect {
                adapter.submitData(it)
            }


        }




        refreshLayout.setOnRefreshListener {
            log("refresh: 当下拉的时候会调用")
            mMainCoroutine.launch {
                adapter.refresh()
                delay(5000)
                refreshLayout.isRefreshing = false

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

    private suspend fun getUserData2() = withContext(Dispatchers.IO) {
        log("协程线程：${android.os.Process.myTid()}")
        delay(1000 * 5)
        val data = mutableListOf<UserItem>()
        for (i in 0..10000) {
            val userItem = UserItem(R.mipmap.ic_launcher, "${i}.name")
            data.add(userItem)
        }
        data
    }


    private suspend fun getUserData() = suspendCoroutine<MutableList<UserItem>> {
        log("协程线程：${android.os.Process.myTid()}")
        val data = mutableListOf<UserItem>()
        for (i in 0..10000) {
            val userItem = UserItem(R.mipmap.ic_launcher, "${i}.name")
            data.add(userItem)
        }

        it.resumeWith(Result.success(data))
    }
}