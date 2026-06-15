package com.marquinhosmorales.f1hub.data.drivers

import com.marquinhosmorales.f1hub.model.drivers.Driver
import com.marquinhosmorales.f1hub.model.wikipedia.WikipediaSummary

interface DriversRepository {
    suspend fun getCurrentDrivers(): List<Driver>
    suspend fun getDriverDetail(driverId: String): Driver?
    suspend fun getWikipediaSummary(title: String): WikipediaSummary
}