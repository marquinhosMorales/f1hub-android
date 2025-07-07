package com.marquinhosmorales.f1hub.data.standings

import com.marquinhosmorales.f1hub.model.standings.StandingsEntry

interface StandingsRepository {
    suspend fun getCurrentDriversStandings(): List<StandingsEntry>
    suspend fun getCurrentTeamsStandings(): List<StandingsEntry>
}