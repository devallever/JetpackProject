package com.allever.app.jetpack.function.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceController {
//    private const val BASE_URL = "https://raw.githubusercontent.com/devallever/DataProject/master/"

    private const val BASE_URL = "https://translate.google.cn/"
    private val mRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = mRetrofit.create(serviceClass)

    //泛型实化：使用inline 和 reified
    inline fun <reified T> create(): T = create(T::class.java)
}