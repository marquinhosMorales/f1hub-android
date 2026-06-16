package com.marquinhosmorales.f1hub.data.wikipedia

import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary
import com.marquinhosmorales.f1hub.network.WikipediaApiService

class WikipediaRepositoryImpl(
    private val wikiApiService: WikipediaApiService
) : WikipediaRepository {
    override suspend fun getWikipediaSummary(title: String): WikipediaSummary {
        return wikiApiService.getPageSummary(title)
    }
}