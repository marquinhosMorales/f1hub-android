package com.marquinhosmorales.f1hub.model.races

import kotlinx.serialization.Serializable

@Serializable
data class RaceSession(
    val date: String?,
    val time: String?
)