package com.example.newsfeedsample.repository

import com.example.newsfeedsample.api.RetrofitInstance
import com.example.newsfeedsample.model.NewsData
import retrofit2.Response

class NewsRepository {
    suspend fun getNews(category: String): Response<NewsData> {
        return RetrofitInstance.api.getNews(category = category)
    }
}