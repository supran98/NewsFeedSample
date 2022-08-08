package com.example.newsfeedsample.testDi

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.ExperimentalTestApi
import com.example.newsfeedsample.MainViewModel
import com.example.newsfeedsample.api.NewsService
import com.example.newsfeedsample.mock.emptyMockResponse
import com.example.newsfeedsample.mock.mockResponseData
import com.example.newsfeedsample.model.ResponseData
import com.example.newsfeedsample.respository.Repository
import com.example.newsfeedsample.tests.ItemsNotRepeatingTest
import com.example.newsfeedsample.tests.SavingPageStateTest
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Qualifier

@Component(modules = [TestNetworkModule::class, TestViewModelModule::class])
@ExperimentalComposeUiApi
@ExperimentalTestApi
interface TestAppComponent {
    fun inject(test: SavingPageStateTest)
    fun inject(test: ItemsNotRepeatingTest)
}

@Module
object TestNetworkModule {

    @Provides
    fun provideNewsService(): NewsService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create()
    }

    @Provides
    @Mock
    fun provideMockNewsService(): NewsService {
        return object : NewsService {
            override suspend fun getNews(
                apiKey: String,
                query: String,
                pageSize: Int,
                page: Int
            ): ResponseData {
                return if (page > 1) {
                    emptyMockResponse
                } else
                    mockResponseData
            }

            override suspend fun getTopHeadlines(
                apiKey: String,
                country: String,
                category: String,
                pageSize: Int,
                page: Int
            ): ResponseData {
                return if (page > 1) {
                    emptyMockResponse
                } else
                    mockResponseData
            }
        }
    }

    @Provides
    fun provideRepository(newsService: NewsService): Repository {
        return Repository(newsService)
    }
    @Provides
    @Mock
    fun provideMockRepository(@Mock newsService: NewsService): Repository {
        return Repository(newsService)
    }
}

@Module
object TestViewModelModule {
    @Provides
    fun provideViewModel(repository: Repository): MainViewModel {
        return MainViewModel(repository)
    }

    @Provides
    @Mock
    fun provideMockViewModel(@Mock repository: Repository): MainViewModel {
        return MainViewModel(repository)
    }
}

@Qualifier
annotation class Mock