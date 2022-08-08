package com.example.newsfeedsample.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsfeedsample.MainViewModel
import com.example.newsfeedsample.ui.AppBarState
import com.example.newsfeedsample.ui.ArticleScreen
import com.example.newsfeedsample.ui.NewsScreen
import com.example.newsfeedsample.ui.Screens

@Composable
fun HostScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.NewsScreen.route
    ) {
        composable(Screens.NewsScreen.route) {
            viewModel.updateAppBarState(AppBarState.SEARCH_CLOSED)
            NewsScreen(navController, viewModel)
        }
        composable(
            route = Screens.ArticleScreen.route,
            arguments = listOf(navArgument("url") {
                type = NavType.StringType
            })
        ) {
            viewModel.updateAppBarState(AppBarState.HIDDEN)
            val articleUrl = it.arguments?.getString("url") ?: ""
            ArticleScreen(url = articleUrl, navController = navController)
        }
    }
}