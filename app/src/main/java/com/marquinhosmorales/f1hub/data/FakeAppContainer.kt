package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriversRepository
import com.marquinhosmorales.f1hub.data.drivers.FakeDriversRepository
import com.marquinhosmorales.f1hub.data.races.FakeRacesRepository
import com.marquinhosmorales.f1hub.data.races.RacesRepository

class FakeAppContainer() : AppContainer {
    override val driversRepository: DriversRepository = FakeDriversRepository()
    override val racesRepository: RacesRepository = FakeRacesRepository()
}