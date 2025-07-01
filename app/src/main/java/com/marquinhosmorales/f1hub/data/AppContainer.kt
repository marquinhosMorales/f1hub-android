package com.marquinhosmorales.f1hub.data

import com.marquinhosmorales.f1hub.data.drivers.DriverRepository
import com.marquinhosmorales.f1hub.data.drivers.FakeDriverRepository

interface AppContainer {
    val driverRepository: DriverRepository
}

class DefaultAppContainer : AppContainer {
    override val driverRepository: DriverRepository = FakeDriverRepository()
}