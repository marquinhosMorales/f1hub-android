package com.marquinhosmorales.f1hub.model.wikipedia

import kotlinx.serialization.Serializable


@Serializable
data class WikipediaImage(
    val source: String,
    val width: Int,
    val height: Int
)