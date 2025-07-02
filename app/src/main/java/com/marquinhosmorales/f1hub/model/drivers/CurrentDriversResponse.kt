package com.marquinhosmorales.f1hub.model.drivers

import kotlinx.serialization.Serializable

@Serializable
data class CurrentDriversResponse(
    val season: Int,
    val championshipId: String,
    val drivers: List<Driver>
)