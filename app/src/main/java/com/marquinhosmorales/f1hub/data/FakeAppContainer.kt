package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.data.drivers.FakeDriversRepository
import com.marquinhosmorales.f1hub.data.races.FakeRacesRepository
import com.marquinhosmorales.f1hub.data.races.RacesRepository
import com.marquinhosmorales.f1hub.data.teams.FakeTeamsRepository
import com.marquinhosmorales.f1hub.data.teams.TeamsRepository
import com.marquinhosmorales.f1hub.data.standings.FakeStandingsRepository
import com.marquinhosmorales.f1hub.data.standings.StandingsRepository
import com.marquinhosmorales.f1hub.data.wikipedia.FakeWikipediaRepository
import com.marquinhosmorales.f1hub.data.wikipedia.WikipediaRepository

class FakeAppContainer() : AppContainer {
    override val driversRepository: DriversRepository = FakeDriversRepository()
    override val wikipediaRepository: WikipediaRepository = FakeWikipediaRepository()
    override val racesRepository: RacesRepository = FakeRacesRepository()
    override val teamsRepository: TeamsRepository = FakeTeamsRepository()
    override val standingsRepository: StandingsRepository = FakeStandingsRepository()
}