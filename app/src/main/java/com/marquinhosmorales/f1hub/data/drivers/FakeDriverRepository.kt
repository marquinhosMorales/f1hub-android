package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver
import kotlinx.coroutines.delay

class FakeDriverRepository : DriverRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        return mockDrivers
    }
}

class FakeNetworkDriverRepository : DriverRepository {
    override suspend fun getCurrentDrivers(): List<Driver> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockDrivers
    }
}