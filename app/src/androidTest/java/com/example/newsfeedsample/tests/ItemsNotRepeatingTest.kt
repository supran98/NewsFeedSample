package com.example.newsfeedsample.tests

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.newsfeedsample.MainViewModel
import com.example.newsfeedsample.mock.mockArticles
import com.example.newsfeedsample.screens.TestNewsScreen
import com.example.newsfeedsample.testDi.DaggerTestAppComponent
import com.example.newsfeedsample.testDi.Mock
import com.example.newsfeedsample.testDi.TestAppComponent
import com.example.newsfeedsample.ui.navigation.HostScreen
import io.github.kakaocup.compose.node.element.ComposeScreen.Companion.onComposeScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@ExperimentalComposeUiApi
@ExperimentalTestApi
class ItemsNotRepeatingTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    val testAppComponent: TestAppComponent = DaggerTestAppComponent.create()

    @Inject
    @Mock
    lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        testAppComponent.inject(this)
    }

    @Test
    fun itemsNotRepeatingTest() {
        composeTestRule.setContent {
            val navController = rememberNavController()
            viewModel.getTopHeadlines("ru", "general")
            HostScreen(navController = navController, viewModel = viewModel)
        }

        val lastItem = mockArticles.size - 1
        onComposeScreen<TestNewsScreen>(composeTestRule) {
            composeTestRule.waitUntil(5000L) {
                isVisible(firstArticle)
            }
            composeTestRule.onNodeWithTag("NewsList").performScrollToIndex(lastItem)
            composeTestRule.onNodeWithTag("article #${lastItem + 1}").assertDoesNotExist()
        }

    }
}