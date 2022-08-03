package com.example.newsfeedsample.model

data class ResponseData(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String,
    val urlToImage: String,
    val url: String,
    val content: String
)