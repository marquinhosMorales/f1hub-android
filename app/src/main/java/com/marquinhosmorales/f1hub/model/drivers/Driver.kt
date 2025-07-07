package com.marquinhosmorales.f1hub.model.drivers

import com.marquinhosmorales.f1hub.model.TeamID
import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val driverId: String? = null,
    val name: String,
    val surname: String,
    val nationality: String? = null,
    val birthday: String,
    val number: Int,
    val shortName: String,
    val url: String,
    val teamId: TeamID? = null,
    val country: String? = null
) {
    val id: String
        get() = driverId ?: name
}
