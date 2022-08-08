package com.example.newsfeedsample.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class TestArticleScreen(semanticsProvider: SemanticsNodeInteractionsProvider)
    : ComposeScreen<TestArticleScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = { hasTestTag("ArticleScreen") }
    ) {
    val webView: KNode = child { hasTestTag("ArticleWebView") }
}