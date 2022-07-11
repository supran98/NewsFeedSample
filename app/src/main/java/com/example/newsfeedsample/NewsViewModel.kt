package com.example.newsfeedsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsfeedsample.api.RetrofitInstance
import com.example.newsfeedsample.model.Article
import com.example.newsfeedsample.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repo = NewsRepository()
    private val news = MutableLiveData<List<Article>>(listOf())

    fun getNews(category: String): Flow<PagingData<Article>> {
        val pagingData = Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true),
            pagingSourceFactory =  { NewsPagingSource(RetrofitInstance.api, category) }
        ).flow
        return news.asFlow().flatMapLatest { pagingData }.cachedIn(viewModelScope)
    }
}