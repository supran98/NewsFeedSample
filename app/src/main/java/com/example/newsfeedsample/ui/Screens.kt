package com.example.newsfeedsample.ui

sealed class Screens(val route: String) {
    object NewsScreen : Screens("news_screen")
    object ArticleScreen : Screens("article_screen/{url}") {
        fun passUrl(url: String): String {
            return "article_screen/$url"
        }
    }
}
