package com.marquinhosmorales.f1hub.model.standings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CurrentTeamsStandingsResponse(
    val season: Int,
    val championshipId: String,
    @SerialName("constructors_championship")
    val teamsStandings: List<StandingsEntry>
)