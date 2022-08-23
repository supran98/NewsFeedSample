package com.example.newsfeedsample.repository

import com.example.newsfeedsample.api.ApiHandler
import com.example.newsfeedsample.api.NewsService
import com.example.newsfeedsample.model.ResponseData
import javax.inject.Inject

class Repository @Inject constructor(
    private val newsService: NewsService
) {
    suspend fun getNews(
        query: String,
        pageSize: Int,
        page: Int
    ): ResponseData {
        return ApiHandler.safeGetNews {
            newsService.getNews(query = query, pageSize = pageSize, page = page)
        }
    }

    suspend fun getTopHeadlines(
        country: String,
        category: String,
        pageSize: Int,
        page: Int
    ): ResponseData {
        return ApiHandler.safeGetNews {
            newsService.getTopHeadlines(
                country = country,
                category = category,
                pageSize = pageSize,
                page = page
            )
        }
    }
}