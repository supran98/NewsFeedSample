package com.example.newsfeedsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.navigation.compose.rememberNavController
import com.example.newsfeedsample.ui.*
import com.example.newsfeedsample.ui.navigation.HostScreen
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalComposeUiApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        viewModel.getTopHeadlines("ru", "general")

        setContent {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            val keyboardController = LocalSoftwareKeyboardController.current

            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier.fillMaxSize(),
                topBar = {
                    NewsAppBar(
                        searchQuery = viewModel.searchWidgetTextState.value,
                        onQueryChanged = {
                            viewModel.updateSearchWidgetTextState(it)
                        },
                        appBarState = viewModel.appBarState.value,
                        onSearchTriggered = {
                            viewModel.updateAppBarState(AppBarState.SEARCH_OPENED)
                        },
                        onSearchClosed = {
                            viewModel.updateAppBarState(AppBarState.SEARCH_CLOSED)
                        },
                        onSearchClicked = { searchQuery ->
                            viewModel.getNews(searchQuery)
                            keyboardController?.hide()
                        },
                        onNavigationIconClicked = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
                drawerContent = {
                    NavDrawer(onClickItem = { category ->
                        scope.launch { scaffoldState.drawerState.close() }
                        viewModel.getTopHeadlines("ru", category)
                    })
                },
                drawerGesturesEnabled = scaffoldState.drawerState.isOpen
            ) {
                HostScreen(navController = navController, viewModel = viewModel)
            }
        }
    }
}