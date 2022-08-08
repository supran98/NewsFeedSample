package com.example.newsfeedsample.mock

import com.example.newsfeedsample.model.Article
import com.example.newsfeedsample.model.ResponseData

val mockArticle = Article(
    title = "Round Up: EVO 2022 Every Nintendo Switch Announcement & More - Nintendo Life",
    description = "New fighter DLC, arcade cabinets, game updates!",
    urlToImage = "https://images.nintendolife.com/9623dbe7df3a9/1280x720.jpg",
    url = "New fighter DLC, arcade cabinets, game updates!",
    content = "Image: PixelHeart, Statera Studio\\r\\nEVO 2022 is taking place this weekend," +
            "and as part of this fighting game celebration, various companies have been " +
            "announcing all sorts of related news. This include… [+4188 chars]"
)

val mockArticles = listOf(
    mockArticle,
    mockArticle,
    mockArticle,
    mockArticle,
    mockArticle
)

val mockResponseData = ResponseData(
    status = "ok",
    totalResults = mockArticles.size,
    articles = mockArticles
)

val emptyMockResponse = ResponseData(
    status = "ok",
    totalResults = 0,
    articles = emptyList()
)