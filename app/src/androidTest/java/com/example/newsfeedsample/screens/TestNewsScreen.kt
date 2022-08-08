package com.example.newsfeedsample.screens

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import io.github.kakaocup.compose.node.element.ComposeScreen
import io.github.kakaocup.compose.node.element.KNode

class TestNewsScreen(semanticsProvider: SemanticsNodeInteractionsProvider)
    : ComposeScreen<TestNewsScreen>(
        semanticsProvider = semanticsProvider,
        viewBuilderAction = {
            hasTestTag("NewsList")
        }
    ) {
    fun articleAt(index: Int): KNode = child {
        hasTestTag("article #$index")
    }

    val firstArticle: KNode = articleAt(0)

    fun isVisible(node: KNode): Boolean {
        return try {
            node.assertIsDisplayed()
            true
        } catch (e: AssertionError) {
            false
        }
    }
}