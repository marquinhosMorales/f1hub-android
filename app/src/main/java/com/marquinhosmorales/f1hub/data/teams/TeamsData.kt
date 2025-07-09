package com.marquinhosmorales.f1hub.data.teams

import com.marquinhosmorales.f1hub.model.Team
import com.marquinhosmorales.f1hub.model.TeamID

val mockRedBull = Team(
    teamId = TeamID.RedBull,
    teamName = "Red Bull Racing",
    country = "Austria",
    firstAppearance = 2005,
    constructorsChampionships = 6,
    driversChampionships = 8,
    url = "https://en.wikipedia.org/wiki/Red_Bull_Racing"
)

val mockMcLaren = Team(
    teamId = TeamID.McLaren,
    teamName = "McLaren Formula 1 Team",
    country = "Great Britain",
    firstAppearance = 1966,
    constructorsChampionships = 9,
    driversChampionships = 12,
    url = "https://en.wikipedia.org/wiki/McLaren"
)

val mockTeams = listOf(mockRedBull, mockMcLaren)