package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.Driver
import com.marquinhosmorales.f1hub.model.TeamID

val mockVerstappen = Driver(
    driverId = "max_verstappen",
    name = "Max",
    surname = "Verstappen",
    nationality = "Netherlands",
    birthday = "30/09/1997",
    number = 33,
    shortName = "VER",
    url = "https://en.wikipedia.org/wiki/Max_Verstappen",
    teamId = TeamID.RedBull,
    country = null,
)

val mockNorris = Driver(
    driverId = "norris",
    name = "Lando",
    surname = "Norris",
    nationality = "Great Britain",
    birthday = "13/11/1999",
    number = 4,
    shortName = "NOR",
    url = "https://en.wikipedia.org/wiki/Lando_Norris",
    teamId = TeamID.McLaren,
    country = null,
)

val mockDrivers = listOf(mockVerstappen, mockNorris)