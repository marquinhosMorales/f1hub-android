package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver

interface DriversRepository {
    suspend fun getCurrentDrivers(): List<Driver>
}