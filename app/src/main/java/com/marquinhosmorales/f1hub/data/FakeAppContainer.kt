package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.data.drivers.FakeDriversRepository
import com.marquinhosmorales.f1hub.data.races.FakeRacesRepository
import com.marquinhosmorales.f1hub.data.races.RacesRepository
import com.marquinhosmorales.f1hub.data.standings.FakeStandingsRepository
import com.marquinhosmorales.f1hub.data.standings.StandingsRepository

class FakeAppContainer() : AppContainer {
    override val driversRepository: DriversRepository = FakeDriversRepository()
    override val racesRepository: RacesRepository = FakeRacesRepository()
    override val standingsRepository: StandingsRepository = FakeStandingsRepository()
}