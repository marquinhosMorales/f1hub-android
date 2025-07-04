package com.marquinhosmorales.f1hub.data.races

import com.marquinhosmorales.f1hub.data.drivers.mockNorris
import com.marquinhosmorales.f1hub.data.teams.mockMcLaren
import com.marquinhosmorales.f1hub.model.Circuit
import com.marquinhosmorales.f1hub.model.TeamID
import com.marquinhosmorales.f1hub.model.races.Race
import com.marquinhosmorales.f1hub.model.races.RaceFastLap
import com.marquinhosmorales.f1hub.model.races.RaceSchedule
import com.marquinhosmorales.f1hub.model.races.RaceSession

val mockAustralia = Race(
    raceId = "australian_2025",
    championshipId = "f1_2025",
    raceName = "Louis Vuitton Australian Grand Prix 2025",
    laps = 58,
    round = 1,
    url = "https://en.wikipedia.org/wiki/2025_Australian_Grand_Prix",
    schedule = RaceSchedule(
        race = RaceSession(
            date = "2025-03-16",
            time = "04:00:00Z"
        ),
        qualy = RaceSession(
            date = "2025-03-15",
            time = "05:00:00Z"
        ),
        fp1 = RaceSession(
            date = "2025-03-14",
            time = "01:30:00Z"
        ),
        fp2 = null,
        fp3 = null,
        sprintQualy = null,
        sprintRace = null
    ),
    circuit = Circuit(
        circuitId = "albert_park",
        circuitName = "Albert Park Circuit",
        country = "Australia",
        city = "Melbourne",
        circuitLength = "5278km",
        lapRecord = "1:19:813",
        firstParticipationYear = 1996,
        corners = 14,
        fastestLapDriverId = "leclerc",
        fastestLapTeamId = "ferrari",
        fastestLapYear = 2024,
        url = "https://en.wikipedia.org/wiki/Albert_Park_Circuit"
    ),
    fastLap = RaceFastLap(
        time = "1:22.167",
        driverId = "norris",
        teamId = TeamID.McLaren
    ),
    winner = mockNorris,
    teamWinner = mockMcLaren
)

val mockSpain = Race(
    raceId = "spanish_2025",
    championshipId = "f1_2025",
    raceName = "Aramco Gran Premio de España 2025",
    laps = 66,
    round = 9,
    url = "https://en.wikipedia.org/wiki/2025_Spanish_Grand_Prix",
    schedule = RaceSchedule(
        race = RaceSession(
            date = "2026-06-01",
            time = "13:00:00Z"
        ),
        qualy = RaceSession(
            date = "2026-05-31",
            time = "14:00:00Z"
        ),
        fp1 = RaceSession(
            date = "2026-05-30",
            time = "11:30:00Z"
        ),
        fp2 = null,
        fp3 = null,
        sprintQualy = null,
        sprintRace = null
    ),
    circuit = Circuit(
        circuitId = "montmelo",
        circuitName = "Circuit de Barcelona-Catalunya",
        country = "Spain",
        city = "Barcelona",
        circuitLength = "4657km",
        lapRecord = "1:16:330",
        firstParticipationYear = 1991,
        corners = 14,
        fastestLapDriverId = "max_verstappen",
        fastestLapTeamId = "red_bull",
        fastestLapYear = 2023,
        url = "https://en.wikipedia.org/wiki/Circuit_de_Barcelona-Catalunya"
    ),
    fastLap = RaceFastLap(
        time = null,
        driverId = null,
        teamId = null
    ),
    winner = null,
    teamWinner = null
)

val mockRaces = listOf(mockAustralia, mockSpain)