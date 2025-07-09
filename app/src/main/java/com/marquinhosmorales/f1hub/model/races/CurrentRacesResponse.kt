package com.marquinhosmorales.f1hub.model.races

import kotlinx.serialization.Serializable

@Serializable
data class CurrentRacesResponse(
    val season: Int,
    val races: List<Race>
)