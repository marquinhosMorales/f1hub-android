package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary

class FakeDriversRepository : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return mockDrivers
    }

    override suspend fun getDriverDetail(driverId: String): Driver? {
        return mockDrivers.find { it.id == driverId }
    }

    override suspend fun getWikipediaSummary(title: String): WikipediaSummary {
        return WikipediaSummary(
            title = title,
            description = "Fake description for $title",
            extract = "Fake bio for $title"
        )
    }
}