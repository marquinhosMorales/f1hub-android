package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaApiService {
    @GET("api/rest_v1/page/summary/{title}")
    suspend fun getPageSummary(
        @Path("title") title: String
    ): WikipediaSummary
}