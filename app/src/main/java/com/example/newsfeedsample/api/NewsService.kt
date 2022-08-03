package com.example.newsfeedsample.api

import com.example.newsfeedsample.model.ResponseData
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface NewsService {
    @GET("everything")
    suspend fun getNews(
        @Header("Authorization") apiKey: String = "apiKey 2327a0e7d3634d0898f99928b09c57a5",
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1
    ): ResponseData

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Header("Authorization") apiKey: String = "apiKey 2327a0e7d3634d0898f99928b09c57a5",
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 30,
        @Query("page") page: Int = 1
    ): ResponseData
}