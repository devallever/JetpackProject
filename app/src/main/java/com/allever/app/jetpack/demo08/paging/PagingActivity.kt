package com.allever.app.jetpack.demo08.paging

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.allever.app.jetpack.R
import com.allever.app.jetpack.ext.log
import kotlinx.android.synthetic.main.activity_main_paging.*
import kotlinx.coroutines.*
import kotlin.coroutines.suspendCoroutine

class PagingActivity: AppCompatActivity() {

    private val mJob = Job()
    private val mMainCoroutine = CoroutineScope(Dispatchers.Main + mJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_paging)

        log("主线程：${android.os.Process.myTid()}" )
        mMainCoroutine.launch {
            val adapter = UserItemAdapter(this@PagingActivity, getUserData2())
            log("当前线程：${android.os.Process.myTid()}" )
            val layoutManager = LinearLayoutManager(this@PagingActivity)
            rvUser.layoutManager = layoutManager
            rvUser.adapter = adapter
        }

        mMainCoroutine.launch {
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mJob.cancel()
    }

    private suspend fun getUserData2()  = withContext(Dispatchers.IO) {
        log("协程线程：${android.os.Process.myTid()}" )
        delay(1000 * 5)
        val data = mutableListOf<UserItem>()
        for (i in 0..10000) {
            val userItem = UserItem(R.mipmap.ic_launcher, "${i}.name")
            data.add(userItem)
        }
        data
    }


    private suspend fun getUserData() = suspendCoroutine<MutableList<UserItem>> {
        log("协程线程：${android.os.Process.myTid()}" )
        val data = mutableListOf<UserItem>()
        for (i in 0..10000) {
            val userItem = UserItem(R.mipmap.ic_launcher, "${i}.name")
            data.add(userItem)
        }

        it.resumeWith(Result.success(data))
    }
}