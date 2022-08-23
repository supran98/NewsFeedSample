package com.example.newsfeedsample

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsfeedsample.model.Article
import com.example.newsfeedsample.paging.NewsPagingSource
import com.example.newsfeedsample.repository.Repository
import com.example.newsfeedsample.ui.AppBarState
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var articlesFlow: Flow<PagingData<Article>> = emptyFlow()
    private val _uiState = mutableStateOf(articlesFlow)
    val uiState: State<Flow<PagingData<Article>>> = _uiState

    private val _appBarState: MutableState<AppBarState> = mutableStateOf(AppBarState.SEARCH_CLOSED)
    val appBarState: State<AppBarState> = _appBarState

    private val _searchWidgetTextState: MutableState<String> = mutableStateOf("")
    val searchWidgetTextState: State<String> = _searchWidgetTextState

    fun getNews(query: String) {
        articlesFlow = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewsPagingSource{ pageSize, page ->
                    repository.getNews(query, pageSize, page)
                }
            }
        ).flow
        _uiState.value = articlesFlow
    }

    fun getTopHeadlines(country: String, category: String) {
        articlesFlow = Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = {
                NewsPagingSource { pageSize, page ->
                    repository.getTopHeadlines(country, category, pageSize, page)
                }
            }
        ).flow
        _uiState.value = articlesFlow
    }

    fun updateAppBarState(update: AppBarState) {
        _appBarState.value = update
    }

    fun updateSearchWidgetTextState(update: String) {
        _searchWidgetTextState.value = update
    }
}