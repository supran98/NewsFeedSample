package com.example.newsfeedsample.api

import com.example.newsfeedsample.BuildConfig
import com.example.newsfeedsample.model.NewsData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Header("Authorization") auth: String = API_KEY,
        @Query("q") category: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Response<NewsData>

    companion object {
        val MAX_PAGE_SIZE = 20
        val API_KEY = BuildConfig.API_KEY
    }
}