package com.example.newsfeedsample.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.newsfeedsample.R

@Composable
fun NewsAppBar(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    appBarState: AppBarState,
    onSearchTriggered: () -> Unit,
    onSearchClosed: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onNavigationIconClicked: () -> Unit
) {
    when (appBarState) {
        AppBarState.SEARCH_OPENED -> {
            SearchAppBar(
                searchQuery = searchQuery,
                onQueryChanged = { onQueryChanged.invoke(it) },
                onCloseSearchIconClicked = { onSearchClosed.invoke() },
                onSearchClicked = { onSearchClicked.invoke(searchQuery) }
            )
        }
        AppBarState.SEARCH_CLOSED -> {
            DefaultAppBar(
                onNavigationIconClicked = {
                    onNavigationIconClicked.invoke()
                },
                onSearchTriggered = {
                    onSearchTriggered.invoke()
                }
            )
        }
        AppBarState.HIDDEN -> {

        }
    }
}

@Composable
fun SearchAppBar(
    searchQuery: String,
    onQueryChanged: (String) -> Unit,
    onCloseSearchIconClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        elevation = AppBarDefaults.TopAppBarElevation,
        color = Color.Blue
    ) {
        TopAppBar {
            TextField(
                value = searchQuery,
                onValueChange = { onQueryChanged.invoke(it) },
                placeholder = {
                    Text(
                        modifier = Modifier.alpha(ContentAlpha.medium),
                        text = "Search news"
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { onCloseSearchIconClicked.invoke() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onSearchClicked.invoke(searchQuery)
                    }
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun DefaultAppBar(
    onNavigationIconClicked: () -> Unit,
    onSearchTriggered: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "News")
        },
        actions = {
            IconButton(onClick = { onSearchTriggered.invoke() }) {
                Icon(painter = painterResource(
                    id = R.drawable.ic_baseline_search_24),
                    contentDescription = null
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClicked.invoke() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            }
        }
    )
}

//title = {
//    Text(text = "News")
//},
//navigationIcon = {
//    Icon(
//        imageVector = Icons.Default.Menu,
//        contentDescription = null
//    )
//}