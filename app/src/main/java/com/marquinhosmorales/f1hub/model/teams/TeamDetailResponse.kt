package com.marquinhosmorales.f1hub.model.teams

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDetailResponse(
    val total: Int,
    @SerialName("team")
    val teams: List<Team>
)