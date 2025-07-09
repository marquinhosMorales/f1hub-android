package com.marquinhosmorales.f1hub.model.races

import com.marquinhosmorales.f1hub.model.TeamID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceFastLap(
    @SerialName("fast_lap")
    val time: String?,
    @SerialName("fast_lap_driver_id")
    val driverId: String?,
    @SerialName("fast_lap_team_id")
    val teamId: TeamID?
)