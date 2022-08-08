package com.example.newsfeedsample.api

import com.example.newsfeedsample.BuildConfig
import com.example.newsfeedsample.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Header("Authorization") apiKey: String = "apiKey ${BuildConfig.API_KEY}",
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1
    ): ResponseData

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("Authorization") apiKey: String = "apiKey ${BuildConfig.API_KEY}",
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1
    ): ResponseData
}