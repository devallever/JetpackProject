package com.allever.app.jetpack.demo05.databinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FragmentViewModel : ViewModel() {
    val text = MutableLiveData<String>()
}