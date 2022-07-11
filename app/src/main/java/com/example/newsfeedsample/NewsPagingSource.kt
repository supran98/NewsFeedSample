package com.example.newsfeedsample

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsfeedsample.api.NewsService
import com.example.newsfeedsample.model.Article
import retrofit2.HttpException

class NewsPagingSource(
    private val service: NewsService,
    private val query: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        if (query.isEmpty()) {
            return LoadResult.Page(emptyList(), prevKey = null, nextKey = null)
        }

        val page = params.key ?: 1
        val pageSize = params.loadSize.coerceAtMost(NewsService.MAX_PAGE_SIZE)

        val response = service.getNews(category = query, page = page, pageSize = pageSize)
        if (response.isSuccessful) {
            val articles = checkNotNull(response.body()).articles
            return LoadResult.Page(articles, null, null)
        } else {
            Log.d("HttpException", "Response is unsuccessful")
            return LoadResult.Error(HttpException(response))
        }
    }

}