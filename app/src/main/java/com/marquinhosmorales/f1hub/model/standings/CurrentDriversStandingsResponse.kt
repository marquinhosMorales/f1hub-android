package com.marquinhosmorales.f1hub.model.standings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentDriversStandingsResponse(
    @SerialName("drivers_championship")
    val driversStandings: List<StandingsEntry>
)
