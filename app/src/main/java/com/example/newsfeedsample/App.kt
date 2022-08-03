package com.example.newsfeedsample

import android.app.Application
import android.content.Context
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.newsfeedsample.di.AppComponent
import com.example.newsfeedsample.di.DaggerAppComponent

@ExperimentalComposeUiApi
class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

@ExperimentalComposeUiApi
val Context.appComponent: AppComponent
get() = when (this) {
    is App -> appComponent
    else -> applicationContext.appComponent
}