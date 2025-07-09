package com.marquinhosmorales.f1hub.data.standings

import com.marquinhosmorales.f1hub.data.drivers.mockNorris
import com.marquinhosmorales.f1hub.data.drivers.mockVerstappen
import com.marquinhosmorales.f1hub.data.teams.mockMcLaren
import com.marquinhosmorales.f1hub.data.teams.mockRedBull
import com.marquinhosmorales.f1hub.model.TeamID
import com.marquinhosmorales.f1hub.model.standings.StandingsEntry

val mockVerstappenEntry = StandingsEntry(
    classificationId = 1,
    teamId = TeamID.RedBull,
    points = 100,
    position = 1,
    wins = 4,
    team = mockRedBull,
    driverId = mockVerstappen.driverId,
    driver = mockVerstappen
)

val mockNorrisEntry = StandingsEntry(
    classificationId = 2,
    teamId = TeamID.McLaren,
    points = 50,
    position = 2,
    wins = 2,
    team = mockMcLaren,
    driverId = mockNorris.driverId,
    driver = mockNorris
)

val mockRedBullEntry = StandingsEntry(
    classificationId = 1,
    teamId = TeamID.RedBull,
    points = 125,
    position = 1,
    wins = 5,
    team = mockRedBull,
    driverId = null,
    driver = null
)

val mockMcLarenEntry = StandingsEntry(
    classificationId = 2,
    teamId = TeamID.McLaren,
    points = 75,
    position = 2,
    wins = 3,
    team = mockMcLaren,
    driverId = null,
    driver = null
)

val mockDriversStandings = listOf(mockVerstappenEntry, mockNorrisEntry)
val mockTeamsStandings = listOf(mockRedBullEntry, mockMcLarenEntry)