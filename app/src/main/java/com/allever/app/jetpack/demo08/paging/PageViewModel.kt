package com.allever.app.jetpack.demo08.paging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

class PageViewModel: ViewModel() {
    val listData = Pager(PagingConfig(pageSize = 20)) {
        UserDataSource()
    }.flow.cachedIn(viewModelScope)
}