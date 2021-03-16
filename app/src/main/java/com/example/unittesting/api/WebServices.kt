package com.example.unittesting.api

import com.example.unittesting.api.model.NewsResponse
import com.example.unittesting.api.model.SourcesResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {

    //how to cache apis using retrofit using interceptors
    @GET("sources")
    suspend fun getSources(@Query("country") country: String): SourcesResponse

    @GET("everything")
    suspend fun getNewsBySourceId(@Query("sources") sources: String): NewsResponse

}