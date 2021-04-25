package com.allever.app.jetpack.demo08.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.allever.app.jetpack.ext.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource: PagingSource<Int, UserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        log("load ${params.key}")
        val currentLoadingPageKey = params.key ?: 1
//        val response = WanAnderoidApi.getService().getPageList(currentLoadingPageKey)

        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

//        val userList = mutableListOf<UserItem>()
//        response.data.datas.map {
//            val user = UserItem(1, it.title)
//            userList.add(user)
//        }

        val userList = getTestData(currentLoadingPageKey)
        //如果没有了数据， next传null就好了
        if (currentLoadingPageKey.plus(1) == 10) {
            return LoadResult.Page(
                data = userList,
                prevKey = prevKey,
                nextKey = null
            )
        }

        return LoadResult.Page(
            data = userList,
            prevKey = prevKey,
            nextKey = currentLoadingPageKey.plus(1)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? {
        //调动 adapter.refresh() 之后
        log("getRefreshKey")
        return 1
    }

    private suspend fun getTestData(pageNum: Int) = withContext(Dispatchers.IO) {
        val result = mutableListOf<UserItem>()
        for (i in pageNum*10+1..pageNum*10+10) {
            val user = UserItem(1, "${i}title")
            result.add(user)
        }
        return@withContext result
    }
}