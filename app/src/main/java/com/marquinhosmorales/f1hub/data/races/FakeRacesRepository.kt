package com.marquinhosmorales.f1hub.data.races

import com.marquinhosmorales.f1hub.model.races.Race
import kotlinx.coroutines.delay

class FakeRacesRepository : RacesRepository {
    override suspend fun getCurrentRaces(): List<Race> {
        return mockRaces
    }
}

class FakeNetworkRacesRepository : RacesRepository {
    override suspend fun getCurrentRaces(): List<Race> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockRaces
    }
}