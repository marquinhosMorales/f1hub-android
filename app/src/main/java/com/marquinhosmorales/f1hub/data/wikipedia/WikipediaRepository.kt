package com.marquinhosmorales.f1hub.data.wikipedia

import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary

interface WikipediaRepository {
    suspend fun getWikipediaSummary(title: String): WikipediaSummary
}