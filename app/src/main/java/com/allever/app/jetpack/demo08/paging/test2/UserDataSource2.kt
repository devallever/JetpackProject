package com.allever.app.jetpack.demo08.paging.test2

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.allever.app.jetpack.demo08.paging.UserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource2() : PagingSource<Int, UserItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserItem>): Int? = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserItem> {
        var currentInt = params.key ?: 1
        val preInt = if (currentInt == 1)  null else currentInt - 1
        var nextInt = if (currentInt == 100) null else currentInt.plus(1)

        return LoadResult.Page(
            data = getTestData(currentInt),
            prevKey = preInt,
            nextKey = nextInt
        )
    }

    private suspend fun getTestData(pageNum: Int) = withContext(Dispatchers.IO) {
        val result = mutableListOf<UserItem>()
        for (i in pageNum*10+1..pageNum*10+9) {
            val userItem = UserItem(i, "${i}.title")
            result.add(userItem)
        }

        return@withContext result
    }
}