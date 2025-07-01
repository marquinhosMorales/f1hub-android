package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.Driver
import kotlinx.coroutines.delay

class FakeDriverRepository : DriverRepository {
    override suspend fun getDrivers(): List<Driver> {
        // Simulate 1-second network delay
        delay(1000L)

        return mockDrivers
    }
}