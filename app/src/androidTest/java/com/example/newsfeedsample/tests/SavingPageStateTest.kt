package com.example.newsfeedsample.tests

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.espresso.Espresso
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsfeedsample.MainViewModel
import com.example.newsfeedsample.screens.TestArticleScreen
import com.example.newsfeedsample.screens.TestNewsScreen
import com.example.newsfeedsample.testDi.DaggerTestAppComponent
import com.example.newsfeedsample.testDi.TestAppComponent
import com.example.newsfeedsample.ui.navigation.HostScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

@RunWith(AndroidJUnit4::class)
@ExperimentalComposeUiApi
@ExperimentalTestApi
class SavingPageStateTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val testAppComponent: TestAppComponent = DaggerTestAppComponent.create()

    @Inject
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        testAppComponent.inject(this)
    }

    @Test
    fun savingPageStateTest() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            viewModel.getTopHeadlines("ru", "general")
            HostScreen(navController = navController, viewModel = viewModel)
        }

        val indexToScroll = 8
        onComposeScreen<TestNewsScreen>(composeTestRule) {
            composeTestRule.waitUntil(5000L) {
                isVisible(firstArticle)
            }
            composeTestRule.onNodeWithTag("NewsList").performScrollToIndex(indexToScroll)
            composeTestRule.onNodeWithTag("article #$indexToScroll").performClick()
        }

        onComposeScreen<TestArticleScreen>(composeTestRule) {
            webView.assertIsDisplayed()
            Espresso.pressBack()
        }

        onComposeScreen<TestNewsScreen>(composeTestRule) {
            composeTestRule.waitUntil {
                isVisible(articleAt(indexToScroll))
            }
        }
    }
}