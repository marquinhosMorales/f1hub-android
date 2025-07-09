package com.marquinhosmorales.f1hub.model.standings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDriversStandingsResponse(
    val season: Int,
    val championshipId: String,
    @SerialName("drivers_championship")
    val driversStandings: List<StandingsEntry>
)
