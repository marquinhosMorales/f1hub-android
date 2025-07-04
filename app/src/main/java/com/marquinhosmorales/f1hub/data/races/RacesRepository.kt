package com.marquinhosmorales.f1hub.data.races

import com.marquinhosmorales.f1hub.model.races.Race

interface RacesRepository {
    suspend fun getCurrentRaces(): List<Race>
}