package com.allever.app.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.allever.app.jetpack.ext.loge
import com.allever.app.jetpack.function.network.ServiceController
import com.allever.app.jetpack.function.network.TranslateApiService
import retrofit2.Response

object Repository {

    private val translateApiService = ServiceController.create<TranslateApiService>()

    fun getUser(userId: String): LiveData<User> {
        val liveData = MutableLiveData<User>()
        liveData.value = User(userId, userId, 0)
        return liveData
    }

    suspend fun translate(q: String = "你好") = getResponseData {
        translateApiService.translate(q)
    }

    private suspend fun <T> getResponseData(block: suspend () -> Response<T>?): T? {
        return try {
            val response = block()
            val responseBody = if (response?.isSuccessful == true) {
                response.body()
            } else {
                loge("请求失败：${response?.raw()?.request()?.url().toString()}${response?.message()}")
                null
            }
            responseBody
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}