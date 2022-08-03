package com.example.newsfeedsample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.newsfeedsample.R

@Composable
fun NavDrawer(onClickItem: (String) -> Unit) {
    DrawerHeader()
    DrawerBody(
        menuItems = listOf(
            MenuItem(title = "General", category = "general", iconDrawableSource = R.drawable.general_news_icon),
            MenuItem(title = "Sports", category = "sports", iconDrawableSource = R.drawable.sport_icon),
            MenuItem(title = "Tech", category = "technology", iconDrawableSource = R.drawable.tech_icon),
            MenuItem(title = "Lifestyle", category = "entertainment", iconDrawableSource = R.drawable.lifestyle_icon)
        ),
        onClickItem = { category ->
            onClickItem.invoke(category)
        }
    )
}

@Composable
fun DrawerHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 64.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.newsapi_logo),
            contentDescription = null
        )
    }
}

@Composable
fun DrawerBody(menuItems: List<MenuItem>, onClickItem: (String) -> Unit) {
    LazyColumn {
        items(menuItems) { item ->
            Row(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clickable { onClickItem.invoke(item.category) }
            ) {
                Image(
                    painter = painterResource(id = item.iconDrawableSource),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp, 20.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = item.title, fontSize = 19.sp)
            }
            Spacer(modifier = Modifier.padding(10.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun DrawerPreview() {
//    DrawerBody(menuItems = listOf(
//        MenuItem("General", R.drawable.general_news_icon),
//        MenuItem("Sports", R.drawable.sport_icon),
//        MenuItem("Technology", R.drawable.tech_icon),
//        MenuItem("Entertainment", R.drawable.lifestyle_icon)
//    ))
}