package com.marquinhosmorales.f1hub.model.drivers

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverDetailResponse(
    @SerialName("driver")
    val drivers: List<Driver>
)
