package com.example.newsfeedsample.api

import com.example.newsfeedsample.model.ResponseData
import retrofit2.HttpException

class ApiHandler {
    companion object {
        suspend fun safeGetNews(request: suspend () -> ResponseData): ResponseData {
            return try {
                request.invoke()
            } catch (e: HttpException) {
                emptyResponseData
            }
        }
    }
}

private val emptyResponseData = ResponseData("error", 0, emptyList())