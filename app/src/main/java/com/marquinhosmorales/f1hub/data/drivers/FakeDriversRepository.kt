package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver
import kotlinx.coroutines.delay

class FakeDriversRepository : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return mockDrivers
    }
}

class FakeNetworkDriversRepository : DriversRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockDrivers
    }
}