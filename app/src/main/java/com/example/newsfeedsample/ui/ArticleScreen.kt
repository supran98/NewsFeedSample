package com.example.newsfeedsample.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ArticleScreen(url: String, navController: NavController) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable { navController.navigate(Screens.NewsScreen.route) }
        .testTag("ArticleScreen")
    ) {
        val state = rememberWebViewState(url = url)
        WebView(
            state = state,
            modifier = Modifier.testTag("ArticleWebView")
        )
    }
}