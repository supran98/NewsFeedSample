package com.example.newsfeedsample.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsfeedsample.model.Article
import com.example.newsfeedsample.model.ResponseData

class NewsPagingSource(
    private val req: suspend (pageSize: Int, page: Int) -> ResponseData
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val position = params.key ?: 0
        val pageSize = params.loadSize
        val currentPage = if (position < pageSize) 1 else position / pageSize + 1

        val articles = req.invoke(pageSize, currentPage).articles
        val nextKey = if (articles.isEmpty()) null else position + pageSize
        val prevKey = if (position == 0) null else position - 1
        return LoadResult.Page(articles, prevKey = prevKey, nextKey = nextKey)
    }
}