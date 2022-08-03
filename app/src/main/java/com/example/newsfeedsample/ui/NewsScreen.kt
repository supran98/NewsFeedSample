package com.example.newsfeedsample.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.newsfeedsample.MainViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun NewsScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val articles = viewModel.uiState.value.collectAsLazyPagingItems()

    Scaffold {
        LazyColumn {
            items(articles) { article ->
                if (article != null) {
                    AsyncImage(
                        model = article.urlToImage,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = article.title,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .clickable {
                                val encodedUrl = URLEncoder.encode(article.url, StandardCharsets.UTF_8.toString())
                                navController.navigate(Screens.ArticleScreen.passUrl(encodedUrl))
                            }
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}