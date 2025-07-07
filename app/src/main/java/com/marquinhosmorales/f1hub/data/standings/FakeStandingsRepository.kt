package com.marquinhosmorales.f1hub.data.standings

import com.marquinhosmorales.f1hub.model.standings.StandingsEntry
import kotlinx.coroutines.delay

class FakeStandingsRepository : StandingsRepository {
    override suspend fun getCurrentDriversStandings(): List<StandingsEntry> {
        return mockDriversStandings
    }

    override suspend fun getCurrentTeamsStandings(): List<StandingsEntry> {
        return mockTeamsStandings
    }
}

class FakeNetworkFakeStandingsRepository : StandingsRepository {
    override suspend fun getCurrentDriversStandings(): List<StandingsEntry> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockDriversStandings
    }

    override suspend fun getCurrentTeamsStandings(): List<StandingsEntry> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockTeamsStandings
    }
}