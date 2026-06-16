package com.marquinhosmorales.f1hub.data.wikipedia

import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary

class FakeWikipediaRepository : WikipediaRepository {
    override suspend fun getWikipediaSummary(title: String): WikipediaSummary {
        return WikipediaSummary(
            title = title,
            description = "Fake description for $title",
            extract = "Fake bio for $title"
        )
    }
}