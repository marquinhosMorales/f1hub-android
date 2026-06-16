package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver

class FakeDriversRepository : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return mockDrivers
    }

    override suspend fun getDriverDetail(driverId: String): Driver? {
        return mockDrivers.find { it.id == driverId }
    }
}