package com.cscorner.newsx.api

import retrofit2.http.Query
import com.cscorner.newsx.models.NewsResponse
import com.cscorner.newsx.util.Constant.Companion.API_key
import retrofit2.Response
import retrofit2.http.GET

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getHeadlines(
        @Query("country")
        countryCode: String="us",
        @Query("Page")
        pageNumber: Int=1,
        @Query("apiKey")
        apikey:String=API_key
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("Page")
        pageNumber: Int=1,
        @Query("apiKey")
        apikey:String=API_key

    ): Response<NewsResponse>

}