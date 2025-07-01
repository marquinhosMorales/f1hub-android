package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.Driver

interface DriverRepository {
    suspend fun getDrivers(): List<Driver>
}