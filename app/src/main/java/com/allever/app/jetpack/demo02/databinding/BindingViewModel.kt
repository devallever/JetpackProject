package com.allever.app.jetpack.demo02.databinding

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.allever.app.jetpack.Repository
import com.allever.app.jetpack.ext.logd
import com.allever.app.jetpack.function.network.TranslationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.math.log

class BindingViewModel: ViewModel() {
//    private val translationResponseLiveData  = MutableLiveData<TranslationResponse>()
//    val translationResponse: LiveData<TranslationResponse> = translationResponseLiveData
    val trans = ObservableField<String>()

    private val mJob = Job()
    private val mMainScope = CoroutineScope(Dispatchers.Main + mJob)

    fun translate(q: String) {
        mMainScope.launch {
            val response = Repository.translate(q)
            if (response == null) {
                logd("请求失败")
                return@launch
            }
//            translationResponseLiveData.value = response
            trans.set(response.sentences?.get(0)?.trans)
            logd("请求结果： $response")
        }
    }
}