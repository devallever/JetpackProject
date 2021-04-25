/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.allever.app.jetpack.demo08.paging

//import com.android.example.paging.pagingwithnetwork.reddit.vo.RedditPost
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * API communication setup
 */
interface WanAnderoidApi {

    @GET("article/list/{page}/json")
    suspend fun getPageList(
            @Path("page") page: Int
    ): PageResponse


    companion object {
        //https://www.wanandroid.com/
        private const val BASE_URL = "https://www.wanandroid.com/"

        private var apiService: WanAnderoidApi? = null

        fun getService(): WanAnderoidApi {
//            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { Log.d("API", it) })
//            logger.level = HttpLoggingInterceptor.Level.BASIC



            if (apiService == null) {
                val client = OkHttpClient.Builder()
//                    .addInterceptor(logger)
                    .build()
                apiService = Retrofit.Builder()
                    .baseUrl(HttpUrl.parse(BASE_URL)!!)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(WanAnderoidApi::class.java);
            }

            return apiService!!
        }
    }
}