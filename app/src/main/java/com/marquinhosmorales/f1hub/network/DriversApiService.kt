package com.marquinhosmorales.f1hub.network

import com.marquinhosmorales.f1hub.model.drivers.CurrentDriversResponse
import retrofit2.http.GET

interface DriversApiService {
    @GET("api/current/drivers")
    suspend fun getCurrentDrivers(): CurrentDriversResponse
}