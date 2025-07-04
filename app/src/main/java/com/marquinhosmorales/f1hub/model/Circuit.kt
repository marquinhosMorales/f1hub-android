package com.marquinhosmorales.f1hub.model

import kotlinx.serialization.Serializable

@Serializable
data class Circuit(
    val circuitId: String,
    val circuitName: String,
    val country: String,
    val city: String,
    val circuitLength: String,
    val lapRecord: String,
    val firstParticipationYear: Int,
    val corners: Int,
    val fastestLapDriverId: String,
    val fastestLapTeamId: String,
    val fastestLapYear: Int,
    val url: String
) {
    val id: String
        get() = circuitId
}
