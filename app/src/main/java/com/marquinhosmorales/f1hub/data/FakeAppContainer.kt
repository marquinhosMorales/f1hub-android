package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriverRepository
import com.marquinhosmorales.f1hub.data.drivers.FakeDriverRepository

class FakeAppContainer() : AppContainer {
    override val driverRepository: DriverRepository = FakeDriverRepository()
}