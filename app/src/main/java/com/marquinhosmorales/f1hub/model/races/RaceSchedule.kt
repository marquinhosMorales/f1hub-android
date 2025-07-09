package com.marquinhosmorales.f1hub.model.races

import kotlinx.serialization.Serializable

@Serializable
data class RaceSchedule(
    val race: RaceSession,
    val qualy: RaceSession,
    val fp1: RaceSession,
    val fp2: RaceSession?,
    val fp3: RaceSession?,
    val sprintQualy: RaceSession?,
    val sprintRace: RaceSession?
)