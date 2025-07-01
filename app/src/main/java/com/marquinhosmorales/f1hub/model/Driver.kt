package com.marquinhosmorales.f1hub.model

import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val driverId: String?,
    val name: String,
    val surname: String,
    val nationality: String?,
    val birthday: String,
    val number: Int,
    val shortName: String,
    val url: String,
    val teamId: TeamID?,
    val country: String?
) {
    val id: String
        get() = driverId ?: name
}
