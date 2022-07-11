package com.example.newsfeedsample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Article(
    val author: @RawValue String,
    val content: @RawValue String,
    val description: @RawValue String,
    val publishedAt: @RawValue String,
    val source: @RawValue Source,
    val title: @RawValue String,
    val url: @RawValue String,
    val urlToImage: @RawValue String
) : Parcelable {
    override fun hashCode(): Int {
        /*
            TODO:
            при навигации по некоторым ссылкам:
            java.lang.NullPointerException: Attempt to invoke virtual method
            'int java.lang.String.hashCode()' on a null object reference

            не удалось найти причину и исправить. при переопределении метода hashCode() ошибка исчезает
         */
        return title.hashCode()
    }
}