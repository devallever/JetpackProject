package com.allever.app.jetpack.demo08.paging.test2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData

class UserViewModel: ViewModel() {
    val userList = Pager(PagingConfig(pageSize = 20), initialKey = 1) {
        UserDataSource2()
    }.liveData.cachedIn(viewModelScope)
}