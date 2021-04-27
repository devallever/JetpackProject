package com.allever.app.jetpack.demo02.databinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allever.app.jetpack.Repository
import com.allever.app.jetpack.ext.log
import com.allever.app.jetpack.function.network.TranslationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class BindingViewModel: ViewModel() {
    private val translationResponseLiveData  = MutableLiveData<TranslationResponse>()
    val translationResponse: LiveData<TranslationResponse> = translationResponseLiveData

    private val mJob = Job()
    private val mMainScope = CoroutineScope(Dispatchers.Main + mJob)

    fun translate(q: String) {
        mMainScope.launch {
            val response = Repository.translate(q)
            if (response == null) {
                log("请求失败")
                return@launch
            }
            translationResponseLiveData.value = response
            log("请求结果： $response")
        }
    }
}