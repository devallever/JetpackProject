package com.allever.app.jetpack.demo08.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.allever.app.jetpack.ext.log

class UserDataSource: PagingSource<Int, UserItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        log("load ${params.key}")
        val currentLoadingPageKey = params.key ?: 1
        val response = WanAnderoidApi.getService().getPageList(currentLoadingPageKey)

        val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

        val userList = mutableListOf<UserItem>()
        response.data.datas.map {
            val user = UserItem(1, it.title)
            userList.add(user)
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
}