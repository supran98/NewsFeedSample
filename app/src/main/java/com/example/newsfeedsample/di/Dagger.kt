package com.example.newsfeedsample.di

import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.newsfeedsample.MainActivity
import com.example.newsfeedsample.MainViewModel
import com.example.newsfeedsample.api.NewsService
import com.example.newsfeedsample.repository.Repository
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Component(modules = [NetworkModule::class, ViewModelModule::class])
@ExperimentalComposeUiApi
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}

@Module
object NetworkModule {

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
    fun provideRepository(newsService: NewsService): Repository {
        return Repository(newsService)
    }
}

@Module
object ViewModelModule {
    @Provides
    fun provideViewModel(repository: Repository): MainViewModel {
        return MainViewModel(repository)
    }
}