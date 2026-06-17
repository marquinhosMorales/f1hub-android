package com.marquinhosmorales.f1hub.model.wikipedia

import kotlinx.serialization.Serializable

@Serializable
data class WikipediaSummary(
    val title: String,
    val description: String,
    val extract: String,
    val thumbnail: WikipediaImage? = null,
    val originalimage: WikipediaImage? = null
)