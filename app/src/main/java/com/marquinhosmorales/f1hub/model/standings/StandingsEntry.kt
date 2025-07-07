package com.marquinhosmorales.f1hub.model.standings

import com.marquinhosmorales.f1hub.model.Team
import com.marquinhosmorales.f1hub.model.TeamID
import com.marquinhosmorales.f1hub.model.drivers.Driver
import kotlinx.serialization.Serializable

@Serializable
data class StandingsEntry(
    val classificationId: Int,
    val teamId: TeamID,
    val points: Int,
    val position: Int,
    val wins: Int?,
    val team: Team,
    val driverId: String?,
    val driver: Driver?
) {
    val id: String
        get() = classificationId.toString()
}